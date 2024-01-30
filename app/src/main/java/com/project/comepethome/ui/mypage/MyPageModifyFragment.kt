package com.project.comepethome.ui.mypage

import android.net.Uri
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.project.comepethome.R
import com.project.comepethome.databinding.FragmentMyPageModifyBinding
import com.project.comepethome.databinding.ItemUserWithdrawBinding
import com.project.comepethome.ui.main.MainActivity
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class MyPageModifyFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var binding: FragmentMyPageModifyBinding

    private lateinit var myPageModifyViewModel: MyPageModifyViewModel

    var loginUserProfileImg: String? = null
    lateinit var loginUserNickName: String
    lateinit var loginUserName: String
    lateinit var loginUserPhoneNumber: String

    private var selectedImageUri: Uri? = null

    // Registers a photo picker activity launcher in single-select mode.
    val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        // Callback is invoked after the user selects a media item or closes the
        // photo picker.
        if (uri != null) {
            selectedImageUri = uri
            binding.imageUserProfileMyPageModify.setImageURI(uri)
        } else {
            Log.d(TAG, "No media selected")
        }
    }

    val TAG = "MyPageModifyFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentMyPageModifyBinding.inflate(layoutInflater)

        myPageModifyViewModel = ViewModelProvider(this)[MyPageModifyViewModel::class.java]

        loginUserProfileImg = arguments?.getString("loginUserProfileImg")
        loginUserNickName = arguments?.getString("loginUserNickName").toString()
        loginUserName = arguments?.getString("loginUserName").toString()
        loginUserPhoneNumber = arguments?.getString("loginUserPhoneNumber").toString()

        initUi()

        Log.d(TAG, "${MainActivity.accessToken}")

        return binding.root
    }

    private fun initUi() {
        binding.run {
            materialToolbarMyPageModify.run {
                setNavigationIcon(R.drawable.ic_back_24dp)
                setNavigationOnClickListener {
                    mainActivity.removeFragment(MainActivity.MYPAGE_MODIFY_FRAGMENT)
                }
            }

            // 유저의 프로필 사진이 없을 때는 기본 프로필
            if (loginUserProfileImg == null) {
                Glide.with(binding.root.context)
                    .load(R.drawable.img_profile)
                    .into(binding.imageUserProfileMyPageModify)
            } else {
                // 유저의 프로필 사진이 있을 때
                Glide.with(binding.root.context)
                    .load(loginUserProfileImg)
                    .into(binding.imageUserProfileMyPageModify)
            }

            // 프로필 설정
            imageUserProfileMyPageModify.setOnClickListener {
                pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }

            // 로그인 한 유저의 닉네임, 이름, 휴대폰 번호 설정
            editTextNicknameMyPageModify.setText(loginUserNickName)
            editTextNameMyPageModify.setText(loginUserName)
            editTextPhoneNumberMyPageModify.setText(loginUserPhoneNumber)

            editTextPhoneNumberMyPageModify.addTextChangedListener(PhoneNumberFormattingTextWatcher())

            // 수정하기
            buttonMyPageModify.setOnClickListener {
                modifyMyInformation()
            }

            // 로그아웃
            textViewLogoutMyPageModify.setOnClickListener {
                mainActivity.removeAllBackStack()
                mainActivity.selectBottomNavigationItem(R.id.home_menu)

                val snackbar = Snackbar.make(binding.root, "로그아웃 되었습니다.", Snackbar.LENGTH_SHORT)
                snackbar.view.elevation = 0f
                snackbar.show()

                MainActivity.isLogIn = false
            }

            // 회원탈퇴
            textViewWithdrawalMyPageModify.setOnClickListener {
                val itemUserWithdrawBinding = ItemUserWithdrawBinding.inflate(layoutInflater)
                val builder = MaterialAlertDialogBuilder(mainActivity)
                builder.setView(itemUserWithdrawBinding.root)
                val dialog = builder.create()

                itemUserWithdrawBinding.buttonCancel.setOnClickListener {
                    dialog.dismiss()
                }

                itemUserWithdrawBinding.buttonWithdraw.setOnClickListener {
                    dialog.dismiss()
                    mainActivity.removeAllBackStack()
                    mainActivity.selectBottomNavigationItem(R.id.home_menu)

                    val snackbar = Snackbar.make(binding.root, "회원탈퇴 되었습니다.", Snackbar.LENGTH_SHORT)
                    snackbar.view.elevation = 0f
                    snackbar.show()

                    MainActivity.isLogIn = false
                }

                dialog.show()
            }

        }
    }

    private fun modifyMyInformation() {

        val newProfile = selectedImageUri
        val newProfileImgUri = newProfile.toString()
        val newNickName = binding.editTextNicknameMyPageModify.text.toString()
        val newName = binding.editTextNameMyPageModify.text.toString()
        val newPhoneNumber = binding.editTextPhoneNumberMyPageModify.text.toString()

        if (newProfile == null) {
            mainActivity.showSnackbar("프로필 이미지를 선택해주세요.")
            return
        }

        val imageFile = File(getRealPathFromURI(newProfile))

        // MultipartBody.Part 으로 변환
        val imagePart: MultipartBody.Part = imageFile.let {
            val requestFile = it.asRequestBody("multipart/form-data".toMediaTypeOrNull())
            MultipartBody.Part.createFormData("files", it.name, requestFile)
        }


        if (newNickName.isEmpty() || newName.isEmpty() || newPhoneNumber.isEmpty()) {
            mainActivity.showSnackbar("입력이 안된 곳이 있습니다.")
        }else {

            myPageModifyViewModel.modifyUserProfile("${MainActivity.accessToken}",newNickName, newName, newPhoneNumber)

            // 받아온 프로필 사진이 없는 유저는 Upload
            if (loginUserProfileImg == null) {
                myPageModifyViewModel.uploadProfileImg("${MainActivity.accessToken}", imagePart)

            } else {
                // 받아온 프로필 사진이 있는 유저는 Update
                myPageModifyViewModel.updateProfileImg("${MainActivity.accessToken}", imagePart)
            }

            val bundle = Bundle()
            bundle.putString("newProfileImgUri", newProfileImgUri)
            bundle.putString("newNickName", newNickName)
            bundle.putString("newName", newName)
            bundle.putString("newPhoneNumber", newPhoneNumber)

            mainActivity.replaceFragment(MainActivity.MYPAGE_FRAGMENT, false, bundle)
        }

    }

    // 이미지 파일을 실제 파일 경로로 변환
    private fun getRealPathFromURI(uri: Uri): String {
        val cursor = context?.contentResolver?.query(uri, null, null, null, null)
        cursor?.let {
            val columnIndex = it.getColumnIndexOrThrow("_data")
            it.moveToFirst()
            val realPath = it.getString(columnIndex)
            it.close()
            return realPath ?: ""
        } ?: run {
            return uri.path ?: ""
        }
    }

}