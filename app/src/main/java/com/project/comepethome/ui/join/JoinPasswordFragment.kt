package com.project.comepethome.ui.join

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
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

    lateinit var joinId: String

    val TAG = "JoinPasswordFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentJoinPasswordBinding.inflate(layoutInflater)

        joinId = arguments?.getString("joinId").toString()

        closeButton()
        enterPassword()

        return binding.root
    }

    private fun closeButton() {
        binding.imageCloseJoinPassword.setOnClickListener {
            mainActivity.removeFragment(MainActivity.JOIN_PASSWORD_FRAGMENT)
        }
    }

    private fun enterPassword() {
        val editTextPassword = binding.editTextPasswordJoinPassword
        val editTextCheckPassword = binding.editTextCheckPasswordJoinPassword
        val layoutPassword = binding.textInputLayoutPasswordJoinPassword
        val layoutCheckPassword = binding.textInputLayoutCheckPasswordJoinPassword
        val nextButton = binding.buttonNextJoinPassword

        // TextWatcher를 사용하여 입력 변화 감지
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // 입력 전에 필요한 작업
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // 입력 중에 필요한 작업
                val password = editTextPassword.text.toString()
                val checkPassword = editTextCheckPassword.text.toString()

                if (password == checkPassword && password.isNotEmpty() && checkPassword.isNotEmpty()) {
                    nextButton.setBackgroundResource(R.drawable.bg_rect_orange_r10)
                    layoutPassword.error = null
                    layoutCheckPassword.error = null
                } else if (password.isNotEmpty()) {
                    layoutPassword.error = null
                    nextButton.setBackgroundResource(R.drawable.bg_rect_gray200_r10)
                } else if (checkPassword.isNotEmpty()) {
                    layoutCheckPassword.error = null
                    nextButton.setBackgroundResource(R.drawable.bg_rect_gray200_r10)
                }

            }

            override fun afterTextChanged(s: Editable?) {
                // 입력 후에 필요한 작업
                val password = editTextPassword.text.toString()
                val checkPassword = editTextCheckPassword.text.toString()

                // 입력된 값이 같을 때 nextButton의 배경색 변경
                if (password == checkPassword && password.isNotEmpty() && checkPassword.isNotEmpty()) {
                    nextButton.setBackgroundResource(R.drawable.bg_rect_orange_r10)
                    layoutPassword.error = null
                    layoutCheckPassword.error = null
                } else if (password.isEmpty()) {
                    layoutPassword.error = "비밀번호를 입력해주세요"
                    nextButton.setBackgroundResource(R.drawable.bg_rect_gray200_r10)
                } else if (checkPassword.isEmpty()) {
                    layoutCheckPassword.error = "비밀번호를 입력해주세요"
                    nextButton.setBackgroundResource(R.drawable.bg_rect_gray200_r10)
                } else {
                    layoutCheckPassword.error = "비밀번호가 일치하지 않습니다."
                    nextButton.setBackgroundResource(R.drawable.bg_rect_gray200_r10)
                }
            }
        }

        // TextWatcher를 각각의 EditText에 연결
        editTextPassword.addTextChangedListener(textWatcher)
        editTextCheckPassword.addTextChangedListener(textWatcher)

        nextButton.setOnClickListener {
            if (editTextPassword.text.toString() == editTextCheckPassword.text.toString() && editTextPassword.text.toString().isNotEmpty() && editTextCheckPassword.text.toString().isNotEmpty()) {

                val joinPassword = editTextPassword.text.toString()

                val bundle = Bundle()
                bundle.putString("joinId", joinId)
                bundle.putString("joinPassword", joinPassword)

                mainActivity.replaceFragment(MainActivity.JOIN_NICKNAME_FRAGMENT, true, bundle)
            }
        }

    }

}