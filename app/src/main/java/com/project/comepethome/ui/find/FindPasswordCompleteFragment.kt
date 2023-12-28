package com.project.comepethome.ui.find

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.comepethome.R
import com.project.comepethome.databinding.FragmentFindPasswordCompleteBinding
import com.project.comepethome.ui.main.MainActivity


class FindPasswordCompleteFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var binding: FragmentFindPasswordCompleteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentFindPasswordCompleteBinding.inflate(layoutInflater)

        moveToLogin()

        return binding.root
    }

    private fun moveToLogin() {
        binding.buttonFindPasswordComplete.setOnClickListener {
            mainActivity.replaceFragment(MainActivity.LOG_IN_FRAGMENT, false, null)
            mainActivity.removeFragment(MainActivity.FIND_PASSWORD_FRAGMENT)
        }
    }

}