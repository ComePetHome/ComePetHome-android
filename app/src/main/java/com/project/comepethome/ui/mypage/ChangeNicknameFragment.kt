package com.project.comepethome.ui.mypage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.comepethome.R
import com.project.comepethome.databinding.FragmentChangeNicknameBinding
import com.project.comepethome.ui.main.MainActivity

class ChangeNicknameFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var binding: FragmentChangeNicknameBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentChangeNicknameBinding.inflate(layoutInflater)

        initUI()

        return binding.root
    }

    private fun initUI() {
        binding.run {
            materialToolbarChangeNickname.run {
                setNavigationIcon(R.drawable.ic_back_24dp)
                setNavigationOnClickListener {
                    mainActivity.removeFragment(MainActivity.CHANGE_NICKNAME_FRAGMENT)
                }
            }

            // 저장하기
            buttonChangeNickname.run {
                setOnClickListener {
                    mainActivity.removeFragment(MainActivity.CHANGE_NICKNAME_FRAGMENT)
                    mainActivity.removeFragment(MainActivity.MYPAGE_MODIFY_FRAGMENT)
                    mainActivity.replaceFragment(MainActivity.MYPAGE_FRAGMENT, false, null)
                }
            }

        }
    }
}