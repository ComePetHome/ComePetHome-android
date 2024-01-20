package com.project.comepethome.ui.board

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.project.comepethome.R
import com.project.comepethome.databinding.FragmentBoardInfoBinding
import com.project.comepethome.ui.main.MainActivity

class BoardInfoFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var binding: FragmentBoardInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentBoardInfoBinding.inflate(layoutInflater)

        initUI()

        return binding.root
    }

    private fun initUI() {
        binding.run {

            mainActivity.hideBottomNavigationView()

            binding.materialToolbarBoardInfo.setNavigationOnClickListener {
                mainActivity.removeFragment(MainActivity.BOARD_INFO_FRAGMENT)
            }

            // ViewPager2
            binding.viewPagerBoardContentsBoardInfo.run {
                viewPagerBoardContentsBoardInfo.adapter = BoardInfoViewPagerAdapter()
                viewPagerBoardContentsBoardInfo.orientation = ViewPager2.ORIENTATION_HORIZONTAL
                springDotsIndicatorBoardContentsBoardInfo.setViewPager2(viewPagerBoardContentsBoardInfo)
            }

            // recyclerView
            binding.recyclerViewBoardCommentBoardInfo.run {
                adapter = BoardInfoCommentAdapter()
                layoutManager = LinearLayoutManager(context)
            }

        }
    }

}