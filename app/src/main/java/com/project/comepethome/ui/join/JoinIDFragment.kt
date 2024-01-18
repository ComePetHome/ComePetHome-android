package com.project.comepethome.ui.join

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
import com.google.android.material.snackbar.Snackbar
import com.project.comepethome.R
import com.project.comepethome.databinding.FragmentJoinIdBinding
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
        enterID()
        checkJoinUserId()
        joinButton()

        return binding.root
    }

    private fun closeButton() {
        binding.imageCloseJoinId.setOnClickListener {
            mainActivity.removeFragment(MainActivity.JOIN_ID_FRAGMENT)
        }
    }

    private fun enterID() {
        val idEditText = binding.editTextIdJoinId
        val nextButton = binding.buttonNextJoinId
        val checkIdText = binding.textCheckIdJoinId

        idEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No implementation needed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // No implementation needed
            }

            override fun afterTextChanged(s: Editable?) {
                // Check if the EditText has text
                val isIdEntered = s?.isNotEmpty() ?: false

                // Set the text color of checkIdText based on whether the EditText has text
                checkIdText.setTextColor(
                    if (isIdEntered) {
                        // Set to orange color when isIdEntered is true
                        ContextCompat.getColor(requireContext(), R.color.orange)
                    } else {
                        // Set to the original color when isIdEntered is false
                        ContextCompat.getColor(requireContext(), R.color.gray200)
                    }
                )

                if (checkIdText.text.toString() != "아이디 중복확인") {
                    checkIdText.text = "아이디 중복확인"
                    nextButton.setBackgroundResource(R.drawable.bg_rect_gray200_r10)
                }

                if (isIdEntered) {

                } else {
                    // 아이디 입력이 없을 때
                    checkIdText.text = "아이디 중복확인"
                    nextButton.setBackgroundResource(R.drawable.bg_rect_gray200_r10)
                }
            }
        })

    }

    private fun checkJoinUserId() {

        binding.textCheckIdJoinId.setOnClickListener {

            val joinId = binding.editTextIdJoinId.text.toString()

            viewModel.checkUserId(joinId,
                {
                    // 성공시
                    binding.textCheckIdJoinId.text = "사용가능한 아이디 입니다"
                    binding.textCheckIdJoinId.setTextColor(ContextCompat.getColor(requireContext(), R.color.orange))
                    binding.buttonNextJoinId.setBackgroundResource(R.drawable.bg_rect_orange_r10)
                },
                {
                    // 실패시
                    binding.textCheckIdJoinId.text = "이미 사용중인 아이디 입니다"
                    binding.textCheckIdJoinId.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray200))
                    binding.buttonNextJoinId.setBackgroundResource(R.drawable.bg_rect_gray200_r10)

                }
            )
        }
    }

    private fun joinButton() {
        binding.buttonNextJoinId.setOnClickListener {
            val joinId = binding.editTextIdJoinId.text.toString()

            if (joinId.isEmpty()) {
                showSnackbar("아이디를 입력 해주세요.")
                return@setOnClickListener
            }

            val checkIdText = binding.textCheckIdJoinId.text.toString()

            when {
                checkIdText == "사용가능한 아이디 입니다" -> {
                    // 중복 확인 성공
                    val bundle = Bundle()
                    bundle.putString("joinId", joinId)
                    mainActivity.replaceFragment(MainActivity.JOIN_PASSWORD_FRAGMENT, true, bundle)
                }
                checkIdText == "이미 사용중인 아이디 입니다" -> {
                    showSnackbar("아이디를 다시 입력해 주세요.")
                }
                else -> {
                    showSnackbar("아이디 중복 확인을 해주세요.")
                }
            }
        }
    }

    private fun showSnackbar(message: String) {
        val snackbar = Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT)
        snackbar.view.elevation = 0f
        snackbar.show()
    }

}