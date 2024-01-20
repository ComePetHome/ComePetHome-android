package com.project.comepethome.ui.board

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.comepethome.databinding.ItemBoardContentsCommentBinding

class BoardInfoCommentAdapter: RecyclerView.Adapter<BoardInfoCommentAdapter.BoardInfoCommentViewHolder>() {

    inner class BoardInfoCommentViewHolder(binding: ItemBoardContentsCommentBinding): RecyclerView.ViewHolder(binding.root) {

        val itemProfileCommentImage : ImageView
        val itemWriterNameCommentText : TextView
        val itemEditCommentImage : ImageView
        val itemContentsCommentTextView : TextView
        val itemDateCommentTextView : TextView

        init {
            itemProfileCommentImage = binding.imageViewItemProfileComment
            itemWriterNameCommentText = binding.textViewItemWriterNameComment
            itemEditCommentImage = binding.imageViewItemEditComment
            itemContentsCommentTextView = binding.textViewItemContentsComment
            itemDateCommentTextView = binding.textViewItemDateComment
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardInfoCommentViewHolder {
        val binding = ItemBoardContentsCommentBinding.inflate(LayoutInflater.from(parent.context))
        val boardInfoCommentViewHolder = BoardInfoCommentViewHolder(binding)

        binding.root.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        return boardInfoCommentViewHolder
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(holder: BoardInfoCommentViewHolder, position: Int) {

    }

}