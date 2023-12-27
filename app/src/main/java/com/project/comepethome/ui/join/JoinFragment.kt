package com.project.comepethome.ui.join

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.comepethome.R
import com.project.comepethome.databinding.FragmentJoinBinding
import com.project.comepethome.ui.main.MainActivity


class JoinFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var binding: FragmentJoinBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentJoinBinding.inflate(layoutInflater)

        closeButton()
        joinButton()

        return binding.root
    }

    private fun closeButton() {
        binding.imageCloseJoin.setOnClickListener {
            mainActivity.removeFragment(MainActivity.JOIN_FRAGMENT)
        }
    }

    private fun joinButton() {
        binding.buttonJoin.setOnClickListener {
            mainActivity.replaceFragment(MainActivity.LOG_IN_FRAGMENT, false, null)
        }
    }

}