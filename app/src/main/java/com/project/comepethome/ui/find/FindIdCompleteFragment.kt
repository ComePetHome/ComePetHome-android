package com.project.comepethome.ui.find

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.comepethome.R
import com.project.comepethome.databinding.FragmentFindIdCompleteBinding
import com.project.comepethome.ui.main.MainActivity

class FindIdCompleteFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var binding: FragmentFindIdCompleteBinding

    lateinit var userName: String
    lateinit var userId: String

    val TAG = "FindIdCompleteFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentFindIdCompleteBinding.inflate(layoutInflater)

        userName = arguments?.getString("userName").toString()
        userId = arguments?.getString("userId").toString()

        initUI()
        moveToLogin()

        return binding.root
    }

    private fun initUI() {
        binding.textViewUserNameFindIdComplete.text = userName
        binding.textViewUserIdFindIdComplete.text = userId
    }

    private fun moveToLogin() {
        binding.buttonFindIdComplete.setOnClickListener {
            mainActivity.replaceFragment(MainActivity.LOG_IN_FRAGMENT, false, null)
            mainActivity.removeFragment(MainActivity.FIND_ID_FRAGMENT)
        }
    }

}