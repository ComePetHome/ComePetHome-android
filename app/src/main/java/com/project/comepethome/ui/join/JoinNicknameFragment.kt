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
import com.project.comepethome.databinding.FragmentJoinNicknameBinding
import com.project.comepethome.ui.main.MainActivity

class JoinNicknameFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var binding: FragmentJoinNicknameBinding

    lateinit var joinId: String
    lateinit var joinPassword: String

    val TAG = "JoinNicknameFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentJoinNicknameBinding.inflate(layoutInflater)

        joinId = arguments?.getString("joinId").toString()
        joinPassword = arguments?.getString("joinPassword").toString()

        closeButton()
        enterNickname()
        moveToNext()

        return binding.root
    }

    private fun closeButton() {
        binding.imageCloseJoinNickname.setOnClickListener {
            mainActivity.removeFragment(MainActivity.JOIN_NICKNAME_FRAGMENT)
        }
    }

    private fun enterNickname() {
        val editTextNickname = binding.editTextNicknameJoinNickname
        val nextButton = binding.buttonNextJoinNickname

        editTextNickname.addTextChangedListener(object : TextWatcher {
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
    }

    private fun moveToNext() {
        binding.buttonNextJoinNickname.setOnClickListener {
            if (binding.editTextNicknameJoinNickname.text?.isNotEmpty() == true) {

                val joinNickname = binding.editTextNicknameJoinNickname.text.toString()

                val bundle = Bundle()
                bundle.putString("joinId", joinId)
                bundle.putString("joinPassword", joinPassword)
                bundle.putString("joinNickname", joinNickname)

                mainActivity.replaceFragment(MainActivity.JOIN_NAME_FRAGMENT, true, bundle)
            }
        }
    }

}