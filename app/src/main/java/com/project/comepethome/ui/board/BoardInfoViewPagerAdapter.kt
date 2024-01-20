package com.project.comepethome.ui.board

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.project.comepethome.databinding.ItemBoardContentsImageBinding

class BoardInfoViewPagerAdapter: RecyclerView.Adapter<BoardInfoViewPagerAdapter.BoardInfoViewPagerViewHolder>() {

    inner class BoardInfoViewPagerViewHolder(binding: ItemBoardContentsImageBinding) : RecyclerView.ViewHolder(binding.root) {

        val imageViewBoardContentViewPager : ImageView

        init {
            imageViewBoardContentViewPager = binding.imageViewBoardContentViewPager
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardInfoViewPagerViewHolder {
        val binding = ItemBoardContentsImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return BoardInfoViewPagerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindViewHolder(holder: BoardInfoViewPagerViewHolder, position: Int) {

    }

}