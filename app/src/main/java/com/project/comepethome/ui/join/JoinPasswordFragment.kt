package com.project.comepethome.ui.join

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.comepethome.R
import com.project.comepethome.databinding.FragmentJoinPasswordBinding
import com.project.comepethome.ui.main.MainActivity

class JoinPasswordFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var binding: FragmentJoinPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentJoinPasswordBinding.inflate(layoutInflater)

        closeButton()
        checkPasswordMatch()

        return binding.root
    }

    private fun closeButton() {
        binding.imageCloseJoinPassword.setOnClickListener {
            mainActivity.removeFragment(MainActivity.JOIN_PASSWORD_FRAGMENT)
        }
    }

    private fun checkPasswordMatch() {
        binding.buttonNextJoinPassword.setOnClickListener {

            val editTextPassword = binding.editTextPasswordJoinPassword.text.toString()
            val editTextCheckPassword = binding.editTextCheckPasswordJoinPassword.text.toString()
            val layoutCheckPasswordLayout = binding.textInputLayoutCheckPasswordJoinPassword

            if (editTextPassword == editTextCheckPassword && editTextPassword.isNotEmpty() && editTextCheckPassword.isNotEmpty()) {
                // Passwords match, clear error
                layoutCheckPasswordLayout.error = null
                mainActivity.replaceFragment(MainActivity.JOIN_NICKNAME_FRAGMENT, true, null)

            } else if (editTextPassword.isEmpty() or editTextCheckPassword.isEmpty()) {
                layoutCheckPasswordLayout.error = "비밀번호를 입력해주세요"
            } else {
                // Passwords don't match, set error
                layoutCheckPasswordLayout.error = "비밀번호가 일치하지 않습니다."
            }
        }

    }

}