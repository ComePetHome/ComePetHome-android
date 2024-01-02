package com.project.comepethome.ui.board

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.comepethome.R
import com.project.comepethome.databinding.FragmentBoardMainBinding
import com.project.comepethome.ui.main.MainActivity

class BoardMainFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var binding: FragmentBoardMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentBoardMainBinding.inflate(layoutInflater)

        return binding.root
    }
}