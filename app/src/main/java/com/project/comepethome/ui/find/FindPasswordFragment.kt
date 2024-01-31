package com.project.comepethome.ui.find

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.project.comepethome.R
import com.project.comepethome.databinding.FragmentFindPasswordBinding
import com.project.comepethome.databinding.ItemJoinEmailBinding
import com.project.comepethome.ui.join.JoinViewModel
import com.project.comepethome.ui.main.MainActivity

class FindPasswordFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var binding: FragmentFindPasswordBinding

    private lateinit var viewModel: JoinViewModel

    val TAG = "FindPasswordFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentFindPasswordBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(this)[JoinViewModel::class.java]

        closeButton()
        checkUserEmail()
        findButton()

        return binding.root
    }

    private fun closeButton() {
        binding.imageCloseFindPassword.setOnClickListener {
            mainActivity.removeFragment(MainActivity.FIND_PASSWORD_FRAGMENT)
        }
    }

    // 이메일 인증
    private fun checkUserEmail() {

        binding.textCheckEmailFindPassword.setOnClickListener {

            val email = binding.editTextEmailFindPassword.text.toString()

            if (email.isEmpty()) {
                mainActivity.showSnackbar("이메일을 입력해 주세요.")
                return@setOnClickListener
            }

            viewModel.sendEmail(email)

            val dialogView = ItemJoinEmailBinding.inflate(layoutInflater)

            val builder = MaterialAlertDialogBuilder(mainActivity)
                .setView(dialogView.root)
                .create()

            builder.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            dialogView.buttonCheckItemJoinEmail.setOnClickListener {
                val certificationNumber = dialogView.editTextCertificationNumberItemJoinEmail.text.toString()

                verification(email, certificationNumber)

                builder.dismiss()
            }

            builder.show()

        }
    }

    private fun verification(userId : String, code : String) {
        viewModel.verificationEmail(
            userId,
            code,
            { successMessage ->
                binding.textviewResultMessageFindPassword.text = successMessage
                binding.buttonFindPassword.setBackgroundResource(R.drawable.bg_rect_orange_r10)
            },
            { failureMessage ->
                binding.textviewResultMessageFindPassword.text = failureMessage
                binding.buttonFindPassword.setBackgroundResource(R.drawable.bg_rect_gray200_r10)
            }
        )

    }

    private fun findButton() {
        binding.buttonFindPassword.setOnClickListener {

            val email = binding.editTextEmailFindPassword.text.toString()

            if (email.isEmpty()) {
                mainActivity.showSnackbar("이메일을 입력해 주세요.")
                return@setOnClickListener
            }

            val resultMessage = binding.textviewResultMessageFindPassword.text.toString()

            when {
                resultMessage == "이메일 인증에 성공했습니다" -> {
                    mainActivity.replaceFragment(MainActivity.CHANGE_PASSWORD_FRAGMENT, true, null)
                }
            }

        }
    }

}