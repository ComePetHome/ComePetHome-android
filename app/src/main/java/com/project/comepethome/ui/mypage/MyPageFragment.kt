package com.project.comepethome.ui.mypage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.project.comepethome.R
import com.project.comepethome.databinding.FragmentMyPageBinding
import com.project.comepethome.ui.main.MainActivity

class MyPageFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var binding: FragmentMyPageBinding

    lateinit var myPageViewModel: MyPageViewModel

    // MyPageModifyFragment 에 주는 값
    var loginUserProfileImg: String? = null
    lateinit var loginUserNickName: String
    lateinit var loginUserName: String
    lateinit var loginUserPhoneNumber: String

    // MyPageModifyFragment 으로 부터 받는 값
    lateinit var newProfileImgUri: String
    lateinit var newNickName: String
    lateinit var newName: String
    lateinit var newPhoneNumber: String


    val TAG = "MyPageFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentMyPageBinding.inflate(layoutInflater)

        myPageViewModel = ViewModelProvider(this)[MyPageViewModel::class.java]

        initUi()
        observeViewModel()

        return binding.root
    }

    private fun initUi() {
        binding.run {
            mainActivity.showBottomNavigationView()

            if (MainActivity.isLogIn && MainActivity.accessToken != null) {
                layoutLoginStatusYes.visibility = View.VISIBLE
                layoutLoginStatusNo.visibility = View.GONE

                myPageViewModel.getUserProfile(MainActivity.accessToken!!)
                myPageViewModel.getUserProfileImg(MainActivity.accessToken!!)

            } else {
                layoutLoginStatusYes.visibility = View.GONE
                layoutLoginStatusNo.visibility = View.VISIBLE
            }

            // 내 정보 수정
            linearLayoutModifyMyPage.setOnClickListener {

                val bundle = Bundle()
                bundle.putString("loginUserProfileImg", loginUserProfileImg)
                bundle.putString("loginUserNickName", loginUserNickName)
                bundle.putString("loginUserName", loginUserName)
                bundle.putString("loginUserPhoneNumber", loginUserPhoneNumber)

                mainActivity.replaceFragment(MainActivity.MYPAGE_MODIFY_FRAGMENT, true, bundle)
                mainActivity.hideBottomNavigationView()
            }

            // 관심 동물
            linearLayoutLikeAnimalMyPage.setOnClickListener {
                mainActivity.replaceFragment(MainActivity.MYPAGE_LIKE_ANIMAL_FRAGMENT, true, null)
                mainActivity.hideBottomNavigationView()
            }

            // 게시판 작성글/댓글
            linearLayoutBoardMyPage.setOnClickListener {
                mainActivity.replaceFragment(MainActivity.MYPAGE_BOARD_FRAGMENT, true, null)
                mainActivity.hideBottomNavigationView()
            }

            // 로그인 하러가기
            layoutGotoLogin.setOnClickListener {
                mainActivity.replaceFragment(MainActivity.LOG_IN_FRAGMENT, true, null)
            }
        }
    }

    private fun observeViewModel() {
        myPageViewModel.userProfileLiveData.observe(viewLifecycleOwner) { userProfile ->

            newNickName = arguments?.getString("newNickName") ?: ""
            newName = arguments?.getString("newName") ?: ""
            newPhoneNumber = arguments?.getString("newPhoneNumber") ?: ""

            val displayNickName = if (newNickName.isNotEmpty()) newNickName else userProfile.nickName
            val displayName = if (newName.isNotEmpty()) newName else userProfile.name
            val displayPhoneNumber = if (newPhoneNumber.isNotEmpty()) newPhoneNumber else userProfile.phoneNumber

            binding.textViewUserNicknameMyPage.text = displayNickName
            binding.textViewUserIdMyPage.text = userProfile.userId

            loginUserNickName = displayNickName
            loginUserName = displayName
            loginUserPhoneNumber = displayPhoneNumber

        }

        myPageViewModel.userProfileImgLiveData.observe(viewLifecycleOwner) { userProfileImgUri ->

            newProfileImgUri = arguments?.getString("newProfileImgUri") ?: ""

            val displayImg = if (newProfileImgUri.isNotEmpty()) newProfileImgUri else userProfileImgUri

            if (displayImg != null) {

                Glide.with(binding.root.context)
                    .load(displayImg)
                    .into(binding.imageUserProfileMyPage)

                loginUserProfileImg = displayImg

            }else {

                Glide.with(binding.root.context)
                    .load(R.drawable.img_profile)
                    .into(binding.imageUserProfileMyPage)

                loginUserProfileImg = null
            }

        }
    }

}