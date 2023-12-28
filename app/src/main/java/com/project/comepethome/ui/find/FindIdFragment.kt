package com.project.comepethome.ui.find

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.comepethome.R
import com.project.comepethome.databinding.FragmentFindIdBinding
import com.project.comepethome.ui.main.MainActivity

class FindIdFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var binding: FragmentFindIdBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentFindIdBinding.inflate(layoutInflater)

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
            mainActivity.replaceFragment(MainActivity.FIND_ID_COMPLETE_FRAGMENT, true, null)
        }
    }

}