package com.project.comepethome.ui.mypage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.comepethome.R
import com.project.comepethome.databinding.FragmentMyPageBinding
import com.project.comepethome.ui.main.MainActivity

class MyPageFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var binding: FragmentMyPageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentMyPageBinding.inflate(layoutInflater)

        initUi()

        return binding.root
    }

    private fun initUi() {
        binding.run {
            mainActivity.showBottomNavigationView()

            if (MainActivity.isLogIn) {
                layoutLoginStatusYes.visibility = View.VISIBLE
                layoutLoginStatusNo.visibility = View.GONE
            } else {
                layoutLoginStatusYes.visibility = View.GONE
                layoutLoginStatusNo.visibility = View.VISIBLE
            }

            // 내 정보 수정
            linearLayoutModifyMyPage.setOnClickListener {
                mainActivity.replaceFragment(MainActivity.MYPAGE_MODIFY_FRAGMENT, true, null)
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
                mainActivity.replaceFragment(MainActivity.LOG_IN_FRAGMENT, false, null)
            }

        }
    }

}