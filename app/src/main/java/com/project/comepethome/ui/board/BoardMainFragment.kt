package com.project.comepethome.ui.board

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
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

        initUI()
        initRecyclerView()
        moveToBoardWrite()

        return binding.root
    }

    private fun initUI() {
        binding.run {
            mainActivity.activityMainBinding.bottomNavigationViewMain.visibility = View.VISIBLE
        }
    }

    private fun initRecyclerView() {
        val recyclerView = binding.recyclerViewBoardMain
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = BoardMainAdapter()
    }

    private fun moveToBoardWrite() {
        binding.floatingActionButtonBoardMain.setOnClickListener {
            mainActivity.replaceFragment(MainActivity.BOARD_WRITE_FRAGMENT, true, null)
            mainActivity.hideBottomNavigationView()
        }

    }
}