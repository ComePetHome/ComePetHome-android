package com.project.comepethome.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.comepethome.R
import com.project.comepethome.databinding.FragmentLogInBinding
import com.project.comepethome.ui.main.MainActivity

class LogInFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var binding: FragmentLogInBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentLogInBinding.inflate(layoutInflater)

        mainActivity.hideBottomNavigationView()

        moveToFindID()
        moveToFindPassword()
        moveToJoin()

        return binding.root
    }

    private fun moveToFindID() {
        binding.textViewIdSearchLogin.setOnClickListener {
            mainActivity.replaceFragment(MainActivity.FIND_ID_FRAGMENT, true, null)
        }
    }

    private fun moveToFindPassword() {
        binding.textViewPasswordSearchLogin.setOnClickListener {
            mainActivity.replaceFragment(MainActivity.FIND_PASSWORD_FRAGMENT, true, null)
        }
    }

    private fun moveToJoin() {
        binding.textViewJoinLogin.setOnClickListener {
            mainActivity.replaceFragment(MainActivity.JOIN_FRAGMENT, true, null)
        }
    }

}