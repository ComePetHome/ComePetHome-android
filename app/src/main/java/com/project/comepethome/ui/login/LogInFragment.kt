package com.project.comepethome.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.comepethome.R
import com.project.comepethome.databinding.FragmentLogInBinding
import com.project.comepethome.ui.main.MainActivity

class LogInFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var binding: FragmentLogInBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentLogInBinding.inflate(layoutInflater)

        mainActivity.hideBottomNavigationView()

        return binding.root
    }

}