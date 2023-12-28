package com.project.comepethome.ui.find

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.comepethome.R
import com.project.comepethome.databinding.FragmentFindPasswordBinding
import com.project.comepethome.ui.main.MainActivity

class FindPasswordFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var binding: FragmentFindPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentFindPasswordBinding.inflate(layoutInflater)

        closeButton()

        return binding.root
    }

    private fun closeButton() {
        binding.imageCloseFindPassword.setOnClickListener {
            mainActivity.removeFragment(MainActivity.FIND_PASSWORD_FRAGMENT)
        }
    }

}