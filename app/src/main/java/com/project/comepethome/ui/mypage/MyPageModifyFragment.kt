package com.project.comepethome.ui.mypage

import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.project.comepethome.R
import com.project.comepethome.databinding.FragmentMyPageModifyBinding
import com.project.comepethome.databinding.ItemUserWithdrawBinding
import com.project.comepethome.ui.main.MainActivity

class MyPageModifyFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var binding: FragmentMyPageModifyBinding

    private lateinit var myPageModifyViewModel: MyPageModifyViewModel

    lateinit var loginUserNickName: String
    lateinit var loginUserName: String
    lateinit var loginUserPhoneNumber: String

    val TAG = "MyPageModifyFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentMyPageModifyBinding.inflate(layoutInflater)

        myPageModifyViewModel = ViewModelProvider(this)[MyPageModifyViewModel::class.java]

        loginUserNickName = arguments?.getString("loginUserNickName").toString()
        loginUserName = arguments?.getString("loginUserName").toString()
        loginUserPhoneNumber = arguments?.getString("loginUserPhoneNumber").toString()

        initUi()

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

        val newNickName = binding.editTextNicknameMyPageModify.text.toString()
        val newName = binding.editTextNameMyPageModify.text.toString()
        val newPhoneNumber = binding.editTextPhoneNumberMyPageModify.text.toString()

        if (newNickName.isEmpty() || newName.isEmpty() || newPhoneNumber.isEmpty()) {
            mainActivity.showSnackbar("입력이 안된 곳이 있습니다.")
        }else {

            myPageModifyViewModel.modifyUserProfile("${MainActivity.accessToken}",newNickName, newName, newPhoneNumber)

            val bundle = Bundle()
            bundle.putString("newNickName", newNickName)
            bundle.putString("newName", newName)
            bundle.putString("newPhoneNumber", newPhoneNumber)

            mainActivity.replaceFragment(MainActivity.MYPAGE_FRAGMENT, false, bundle)
        }

    }

}