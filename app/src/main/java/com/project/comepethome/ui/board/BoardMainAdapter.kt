package com.project.comepethome.ui.board

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.comepethome.databinding.ItemBoardMainListBinding

class BoardMainAdapter : RecyclerView.Adapter<BoardMainAdapter.BoardMainViewHolder>() {

    inner class BoardMainViewHolder(binding: ItemBoardMainListBinding) : RecyclerView.ViewHolder(binding.root) {

        val itemBoardType: Button
        val itemUserProfile: ImageView
        val itemUserName: TextView
        val itemBoardCreationTime: TextView
        val itemBoardEdit: ImageView
        val itemBoardTitle: TextView
        val itemBoardContent: TextView
        val itemBoard: ImageView
        val itemBoardLikeCount: TextView
        val itemBoardCommentCount: TextView

        init {
            itemBoardType = binding.buttonItemBoardType
            itemUserProfile = binding.imageItemUserProfile
            itemUserName = binding.textViewItemUserName
            itemBoardCreationTime = binding.textViewItemBoardCreationTime
            itemBoardEdit = binding.imageItemBoardEdit
            itemBoardTitle = binding.textViewItemBoardTitle
            itemBoardContent = binding.textViewItemBoardContent
            itemBoard = binding.imageItemBoard
            itemBoardLikeCount = binding.textViewItemBoardLikeCount
            itemBoardCommentCount = binding.textViewItemBoardCommentCount
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardMainViewHolder {

        val binding = ItemBoardMainListBinding.inflate(LayoutInflater.from(parent.context))
        val boardMainViewHolder = BoardMainViewHolder(binding)

        binding.root.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        return boardMainViewHolder
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: BoardMainViewHolder, position: Int) {

    }

}