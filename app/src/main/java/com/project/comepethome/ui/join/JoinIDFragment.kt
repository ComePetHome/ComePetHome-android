package com.project.comepethome.ui.join

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.project.comepethome.R
import com.project.comepethome.databinding.FragmentJoinIdBinding
import com.project.comepethome.databinding.ItemJoinEmailBinding
import com.project.comepethome.ui.main.MainActivity


class JoinIDFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var binding: FragmentJoinIdBinding

    private lateinit var viewModel: JoinViewModel

    val TAG = "JoinIDFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentJoinIdBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(this)[JoinViewModel::class.java]

        closeButton()
        checkJoinUserEmail()
        joinButton()

        return binding.root
    }

    private fun closeButton() {
        binding.imageCloseJoinId.setOnClickListener {
            mainActivity.removeFragment(MainActivity.JOIN_ID_FRAGMENT)
        }
    }

    // 이메일 인증
    private fun checkJoinUserEmail() {

        binding.textCheckIdJoinId.setOnClickListener {

            val joinId = binding.editTextIdJoinId.text.toString()

            viewModel.sendEmail(joinId)

            val dialogView = ItemJoinEmailBinding.inflate(layoutInflater)

            val builder = MaterialAlertDialogBuilder(mainActivity)
                .setView(dialogView.root)
                .create()

            // 배경색을 투명으로 설정
            builder.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            dialogView.buttonCheckItemJoinEmail.setOnClickListener {
                val certificationNumber = dialogView.editTextCertificationNumberItemJoinEmail.text.toString()

                certification(joinId, certificationNumber)

                builder.dismiss()
            }

            builder.show()

        }
    }

    private fun certification(userId : String, code : String){
        viewModel.certificationEmail(
            userId,
            code,
            { successMessage ->
                binding.textviewResultMessageJoinId.text = successMessage
                binding.buttonNextJoinId.setBackgroundResource(R.drawable.bg_rect_orange_r10)
            },
            { failureMessage ->
                binding.textviewResultMessageJoinId.text = failureMessage
                binding.buttonNextJoinId.setBackgroundResource(R.drawable.bg_rect_gray200_r10)
            }

        )
    }

    private fun joinButton() {
        binding.buttonNextJoinId.setOnClickListener {
            val joinId = binding.editTextIdJoinId.text.toString()

            if (joinId.isEmpty()) {
                mainActivity.showSnackbar("이메일을 입력해 주세요.")
                return@setOnClickListener
            }

            val resultMessage = binding.textviewResultMessageJoinId.text.toString()

            when {
                resultMessage == "이메일 인증에 성공했습니다" -> {
                    val bundle = Bundle()
                    bundle.putString("joinId", joinId)
                    mainActivity.replaceFragment(MainActivity.JOIN_PASSWORD_FRAGMENT, true, bundle)
                }
            }
        }
    }

}