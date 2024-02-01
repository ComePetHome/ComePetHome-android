package com.project.comepethome.ui.find

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.project.comepethome.R
import com.project.comepethome.databinding.FragmentChangePasswordBinding
import com.project.comepethome.ui.join.JoinViewModel
import com.project.comepethome.ui.main.MainActivity


class ChangePasswordFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var binding: FragmentChangePasswordBinding

    private lateinit var joinViewModel: JoinViewModel

    val TAG = "ChangePasswordFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentChangePasswordBinding.inflate(layoutInflater)

        joinViewModel = ViewModelProvider(this)[JoinViewModel::class.java]

        closeButton()
        enterPassword()

        return binding.root
    }

    private fun closeButton() {
        binding.imageCloseChangePassword.setOnClickListener {
            mainActivity.removeFragment(MainActivity.CHANGE_PASSWORD_FRAGMENT)
        }
    }

    private fun enterPassword() {
        val editTextNewPassword = binding.editTextNewPasswordChangePassword
        val editTextCheckNewPassword = binding.editTextCheckNewPasswordChangePassword
        val layoutNewPassword = binding.textInputLayoutNewPasswordChangePassword
        val layoutCheckNewPassword = binding.textInputLayoutCheckNewPasswordChangePassword
        val changeButton = binding.buttonChangePassword

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val newPassword = editTextNewPassword.text.toString()
                val checkNewPassword = editTextCheckNewPassword.text.toString()

                if (newPassword == checkNewPassword && newPassword.isNotEmpty() && checkNewPassword.isNotEmpty()) {
                    layoutCheckNewPassword.error = null
                    layoutNewPassword.error = null
                    changeButton.setBackgroundResource(R.drawable.bg_rect_orange_r10)
                } else if (newPassword.isNotEmpty()) {
                    layoutNewPassword.error = null
                    changeButton.setBackgroundResource(R.drawable.bg_rect_gray200_r10)
                } else if (checkNewPassword.isNotEmpty()) {
                    layoutCheckNewPassword.error = null
                    changeButton.setBackgroundResource(R.drawable.bg_rect_gray200_r10)
                } else if (newPassword.isEmpty()) {
                    layoutNewPassword.error = "비밀번호를 입력해주세요"
                    changeButton.setBackgroundResource(R.drawable.bg_rect_gray200_r10)
                } else if (checkNewPassword.isEmpty()) {
                    layoutCheckNewPassword.error = "비밀번호를 입력해주세요"
                    changeButton.setBackgroundResource(R.drawable.bg_rect_gray200_r10)
                }
            }

            override fun afterTextChanged(s: Editable?) {
                val newPassword = editTextNewPassword.text.toString()
                val checkNewPassword = editTextCheckNewPassword.text.toString()

                if (newPassword == checkNewPassword && newPassword.isNotEmpty() && checkNewPassword.isNotEmpty()) {
                    layoutCheckNewPassword.error = null
                    layoutNewPassword.error = null
                    changeButton.setBackgroundResource(R.drawable.bg_rect_orange_r10)
                } else if (newPassword.isEmpty()) {
                    layoutNewPassword.error = "비밀번호를 입력해주세요"
                    changeButton.setBackgroundResource(R.drawable.bg_rect_gray200_r10)
                } else if (checkNewPassword.isEmpty()) {
                    layoutCheckNewPassword.error = "비밀번호를 입력해주세요"
                    changeButton.setBackgroundResource(R.drawable.bg_rect_gray200_r10)
                } else {
                    layoutCheckNewPassword.error = "비밀번호가 일치하지 않습니다."
                    changeButton.setBackgroundResource(R.drawable.bg_rect_gray200_r10)
                }

            }

        }

        editTextNewPassword.addTextChangedListener(textWatcher)
        editTextCheckNewPassword.addTextChangedListener(textWatcher)

        // 변경하기 버튼 클릭
        changeButton.setOnClickListener {

            val newPassword = editTextNewPassword.text.toString()
            val checkNewPassword = editTextCheckNewPassword.text.toString()

            if (newPassword == checkNewPassword && newPassword.isNotEmpty() && checkNewPassword.isNotEmpty()) {

                joinViewModel.changeUserPassword(
                    "${MainActivity.accessToken}",
                    editTextNewPassword.text.toString(),
                    { // 성공
                        mainActivity.replaceFragment(MainActivity.LOG_IN_FRAGMENT, false, null)
                        mainActivity.removeFragment(MainActivity.CHANGE_PASSWORD_FRAGMENT)
                        mainActivity.removeFragment(MainActivity.FIND_PASSWORD_FRAGMENT)
                        mainActivity.showSnackbar("비밀번호 변경에 성공했습니다.")

                        MainActivity.accessToken = null

                    },
                    { // 실패
                        mainActivity.removeFragment(MainActivity.CHANGE_PASSWORD_FRAGMENT)
                        mainActivity.showSnackbar("비밀번호 변경에 실패했습니다.")
                    }
                )
            } else if (newPassword.isEmpty() or checkNewPassword.isEmpty()) {
                mainActivity.showSnackbar("비밀번호를 입력해 주세요.")
                return@setOnClickListener
            }

        }

    }

}