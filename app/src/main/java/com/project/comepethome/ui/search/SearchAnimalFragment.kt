package com.project.comepethome.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.comepethome.R
import com.project.comepethome.databinding.FragmentSearchAnimalBinding
import com.project.comepethome.ui.main.MainActivity

class SearchAnimalFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var binding: FragmentSearchAnimalBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentSearchAnimalBinding.inflate(layoutInflater)

        return binding.root
    }

}