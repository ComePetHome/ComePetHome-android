package com.project.comepethome.ui.mypage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.comepethome.R
import com.project.comepethome.databinding.FragmentMyPageBoardBinding
import com.project.comepethome.ui.main.MainActivity

class MyPageBoardFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var binding: FragmentMyPageBoardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentMyPageBoardBinding.inflate(layoutInflater)

        initUi()

        return binding.root
    }

    private fun initUi() {
        binding.run {
            materialToolbarMyPageBoard.run {
                setNavigationIcon(R.drawable.ic_back_24dp)
                setNavigationOnClickListener {
                    mainActivity.removeFragment(MainActivity.MYPAGE_BOARD_FRAGMENT)
                }
            }

            // 처음에는 작성 글 리사이클러뷰만 보이게하기
            recyclerViewBoardWriteMyPageBoard.run {
                layoutManager = LinearLayoutManager(context)
                adapter = MyPageBoardWriteAdapter()
            }
        }
    }

}