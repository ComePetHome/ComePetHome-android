package com.project.comepethome.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.comepethome.R
import com.project.comepethome.databinding.FragmentHomeBinding
import com.project.comepethome.ui.main.MainActivity


class HomeFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentHomeBinding.inflate(layoutInflater)

        return binding.root
    }

}