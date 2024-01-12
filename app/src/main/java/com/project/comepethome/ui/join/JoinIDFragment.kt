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
import com.project.comepethome.databinding.FragmentJoinIdBinding
import com.project.comepethome.ui.main.MainActivity


class JoinIDFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var binding: FragmentJoinIdBinding

    val TAG = "JoinIDFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentJoinIdBinding.inflate(layoutInflater)

        closeButton()
        enterID()
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

                // Set the background color of the button based on whether the EditText has text
                nextButton.setBackgroundResource(
                    if (isIdEntered) R.drawable.bg_rect_orange_r10
                    else R.drawable.bg_rect_gray200_r10
                )
            }
        })

    }

    private fun joinButton() {
        binding.buttonNextJoinId.setOnClickListener {
            val joinId = binding.editTextIdJoinId.text.toString()

            if (joinId.isNotEmpty()) {
                val bundle = Bundle()
                bundle.putString("joinId", joinId)

                mainActivity.replaceFragment(MainActivity.JOIN_PASSWORD_FRAGMENT, true, bundle)
            }
        }
    }

}