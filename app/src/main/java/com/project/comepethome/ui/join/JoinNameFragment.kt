package com.project.comepethome.ui.join

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.comepethome.R
import com.project.comepethome.databinding.FragmentJoinNameBinding
import com.project.comepethome.ui.main.MainActivity

class JoinNameFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var binding: FragmentJoinNameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentJoinNameBinding.inflate(layoutInflater)

        closeButton()
        enterName()
        moveToNext()

        return binding.root
    }

    private fun closeButton() {
        binding.imageCloseJoinName.setOnClickListener {
            mainActivity.removeFragment(MainActivity.JOIN_NAME_FRAGMENT)
        }
    }

    private fun enterName() {
        val editTextName = binding.editTextNameJoinName
        val nextButton = binding.buttonNextJoinName

        editTextName.addTextChangedListener(object : TextWatcher {
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
        binding.buttonNextJoinName.setOnClickListener {
            if (binding.editTextNameJoinName.text?.isNotEmpty() == true) {
                mainActivity.replaceFragment(MainActivity.JOIN_PHONE_NUMBER_FRAGMENT, true, null)
            }
        }
    }

}