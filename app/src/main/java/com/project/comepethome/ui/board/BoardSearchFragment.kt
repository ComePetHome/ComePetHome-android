package com.project.comepethome.ui.board

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.comepethome.R
import com.project.comepethome.databinding.FragmentBoardSearchBinding
import com.project.comepethome.ui.main.MainActivity

class BoardSearchFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var binding: FragmentBoardSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentBoardSearchBinding.inflate(layoutInflater)

        initUI()
        cancelSearch()

        return binding.root
    }

    private fun initUI() {
        binding.run {
            mainActivity.activityMainBinding.bottomNavigationViewMain.visibility = View.GONE
        }
    }

    private fun cancelSearch() {
        binding.textViewCancelBoardSearch.setOnClickListener {
            mainActivity.removeFragment(MainActivity.BOARD_SEARCH_FRAGMENT)
        }
    }

}