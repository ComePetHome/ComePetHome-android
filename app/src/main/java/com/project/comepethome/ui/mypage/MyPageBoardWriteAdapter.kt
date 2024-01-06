package com.project.comepethome.ui.mypage

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.comepethome.databinding.ItemMyPageBoardBinding

class MyPageBoardWriteAdapter: RecyclerView.Adapter<MyPageBoardWriteAdapter.MyPageBoardWriteHolder>() {

    inner class MyPageBoardWriteHolder(binding: ItemMyPageBoardBinding): RecyclerView.ViewHolder(binding.root) {

        val buttonBoardType : Button
        val textViewBoardTitle : TextView
        val textViewBoardContents : TextView
        val textViewBoardDate : TextView

        init {
            buttonBoardType = binding.buttonBoardTypeItemMyPageBoard
            textViewBoardTitle = binding.textViewBoardTitleItemMyPageBoard
            textViewBoardContents = binding.textViewBoardContentsItemMyPageBoard
            textViewBoardDate = binding.textViewBoardDateItemMyPageBoard
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPageBoardWriteHolder {

        val binding = ItemMyPageBoardBinding.inflate(LayoutInflater.from(parent.context))
        val myPageBoardWriteHolder = MyPageBoardWriteHolder(binding)

        binding.root.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        return myPageBoardWriteHolder
    }

    override fun getItemCount(): Int {
        return 2
    }

    override fun onBindViewHolder(holder: MyPageBoardWriteHolder, position: Int) {

    }

}