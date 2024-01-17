package com.project.comepethome.ui.join

import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.project.comepethome.R
import com.project.comepethome.databinding.FragmentJoinPhoneNumberBinding
import com.project.comepethome.ui.main.MainActivity


class JoinPhoneNumberFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var binding: FragmentJoinPhoneNumberBinding

    lateinit var joinId: String
    lateinit var joinPassword: String
    lateinit var joinNickname: String
    lateinit var joinName: String

    private lateinit var viewModel: JoinViewModel

    val TAG = "JoinPhoneNumberFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentJoinPhoneNumberBinding.inflate(layoutInflater)

        joinId = arguments?.getString("joinId").toString()
        joinPassword = arguments?.getString("joinPassword").toString()
        joinNickname = arguments?.getString("joinNickname").toString()
        joinName = arguments?.getString("joinName").toString()

        viewModel = ViewModelProvider(this)[JoinViewModel::class.java]

        closeButton()
        enterPhoneNumber()
        moveToNext()

        return binding.root
    }

    private fun closeButton() {
        binding.imageCloseJoinPhoneNumber.setOnClickListener {
            mainActivity.removeFragment(MainActivity.JOIN_PHONE_NUMBER_FRAGMENT)
        }
    }

    private fun enterPhoneNumber() {
        val editTextPhoneNumber = binding.editTextPhoneNumberJoinPhoneNumber
        val nextButton = binding.buttonNextJoinPhoneNumber

        editTextPhoneNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No implementation needed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // No implementation needed
            }

            override fun afterTextChanged(s: Editable?) {
                // Check if the EditText has text
                val isIdEntered = s?.isNotEmpty() ?: false

                // Set the background color of the button based on whether the EditText has text
                nextButton.setBackgroundResource(
                    if (isIdEntered) R.drawable.bg_rect_orange_r10
                    else R.drawable.bg_rect_gray200_r10
                )
            }
        })

        // 전화번호를 입력했을시 자동으로 하이픈 생성
        editTextPhoneNumber.addTextChangedListener(PhoneNumberFormattingTextWatcher())
    }

    private fun moveToNext() {
        binding.buttonNextJoinPhoneNumber.setOnClickListener {
            if (binding.editTextPhoneNumberJoinPhoneNumber.text?.isNotEmpty() == true) {
                val joinPhoneNumber = binding.editTextPhoneNumberJoinPhoneNumber.text.toString()

                viewModel.joinUser(
                    joinId,
                    joinPassword,
                    joinNickname,
                    joinName,
                    joinPhoneNumber,
                    {
                        // 성공 시
                        val fragmentManager = mainActivity.supportFragmentManager
                        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                        mainActivity.replaceFragment(MainActivity.LOG_IN_FRAGMENT, false, null)

                        val snackbar = Snackbar.make(binding.root, "회원가입이 완료되었습니다.", Snackbar.LENGTH_SHORT)
                        snackbar.view.elevation = 0f
                        snackbar.show()
                    },
                    { errorMessage ->
                        // 실패 시
                        Log.d(TAG, errorMessage)
                        val snackbar = Snackbar.make(binding.root, "회원가입에 실패하였습니다.", Snackbar.LENGTH_SHORT)
                        snackbar.view.elevation = 0f
                        snackbar.show()
                    }
                )
            }
        }
    }

}