package com.project.comepethome.ui.mypage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.comepethome.R
import com.project.comepethome.databinding.FragmentChangePhoneNumberBinding
import com.project.comepethome.ui.main.MainActivity

class ChangePhoneNumberFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var binding: FragmentChangePhoneNumberBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentChangePhoneNumberBinding.inflate(layoutInflater)

        initUI()

        return binding.root
    }

    private fun initUI() {
        binding.run {
            materialToolbarChangePhoneNumber.run {
                setNavigationIcon(R.drawable.ic_back_24dp)
                setNavigationOnClickListener {
                    mainActivity.removeFragment(MainActivity.CHANGE_PHONE_NUMBER_FRAGMENT)
                }
            }

            // 저장하기
            buttonChangePhoneNumber.run {
                setOnClickListener {
                    mainActivity.removeFragment(MainActivity.CHANGE_PHONE_NUMBER_FRAGMENT)
                    mainActivity.removeFragment(MainActivity.MYPAGE_MODIFY_FRAGMENT)
                    mainActivity.replaceFragment(MainActivity.MYPAGE_FRAGMENT, false, null)
                }
            }

        }
    }

}