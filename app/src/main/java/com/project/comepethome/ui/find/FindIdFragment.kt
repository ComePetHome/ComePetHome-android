package com.project.comepethome.ui.find

import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.project.comepethome.R
import com.project.comepethome.databinding.FragmentFindIdBinding
import com.project.comepethome.ui.main.MainActivity

class FindIdFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var binding: FragmentFindIdBinding

    lateinit var viewModel: FindIdViewModel

    val TAG = "FindIdFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentFindIdBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(this)[FindIdViewModel::class.java]

        binding.editTextPhoneNumberFindId.addTextChangedListener(PhoneNumberFormattingTextWatcher())

        closeButton()
        findButton()

        return binding.root
    }

    private fun closeButton() {
        binding.imageCloseFindId.setOnClickListener {
            mainActivity.removeFragment(MainActivity.FIND_ID_FRAGMENT)
        }
    }

    private fun findButton() {
        binding.buttonFindId.setOnClickListener {

            val name = binding.editTextNameFindId.text.toString()
            val phoneNumber = binding.editTextPhoneNumberFindId.text.toString()

            viewModel.findUserId(
                name,
                phoneNumber,
                { userId ->
                    // 성공시
                    val bundle = Bundle()
                    bundle.putString("userName", name)
                    bundle.putString("userId", userId)

                    mainActivity.replaceFragment(MainActivity.FIND_ID_COMPLETE_FRAGMENT, true, bundle)
                },
                { errorMessage ->
                    // 실패 시
                    val snackbar = Snackbar.make(binding.root, "아이디를 찾을 수 없습니다.", Snackbar.LENGTH_SHORT)
                    snackbar.view.elevation = 0f
                    snackbar.show()
                }

            )

        }
    }

}