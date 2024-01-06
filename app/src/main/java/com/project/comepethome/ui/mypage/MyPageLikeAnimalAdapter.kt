package com.project.comepethome.ui.mypage

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.comepethome.databinding.ItemHomePetListBinding

class MyPageLikeAnimalAdapter: RecyclerView.Adapter<MyPageLikeAnimalAdapter.MyPageLikeAnimalViewHolder>() {

    inner class MyPageLikeAnimalViewHolder(binding: ItemHomePetListBinding): RecyclerView.ViewHolder(binding.root) {

        val itemPetLikeImage: ImageView
        val itemPetImage: ImageView
        val itemPetInfoTextView: TextView

        init {
            itemPetLikeImage = binding.imageItemPetLike
            itemPetImage = binding.imageItemPet
            itemPetInfoTextView = binding.textViewItemPetInfo
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPageLikeAnimalViewHolder {

        val binding = ItemHomePetListBinding.inflate(LayoutInflater.from(parent.context))
        val myPageLikeAnimalViewHolder = MyPageLikeAnimalViewHolder(binding)

        binding.root.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        return myPageLikeAnimalViewHolder
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: MyPageLikeAnimalViewHolder, position: Int) {

    }

}