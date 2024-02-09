package com.project.comepethome.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.comepethome.R
import com.project.comepethome.data.model.PetInfo
import com.project.comepethome.databinding.ItemHomePetListBinding
import com.project.comepethome.ui.main.MainActivity

class HomeAdapter(private val listener: OnItemClickListener): RecyclerView.Adapter<HomeAdapter.HomeViewHolder>(){

    private val allPetsInfoList = ArrayList<PetInfo>()

    inner class HomeViewHolder(binding: ItemHomePetListBinding): RecyclerView.ViewHolder(binding.root) {

        val itemPetLikeImage: ImageView
        val itemPetImage: ImageView
        val itemPetInfoNameTextView: TextView
        val itemPetInfoAgeTextView: TextView
        val itemPetInfoSexTextView: TextView
        val itemPetInfoCenterTextView: TextView

        init {
            itemPetLikeImage = binding.imageItemPetLike
            itemPetImage = binding.imageItemPet
            itemPetInfoNameTextView = binding.textViewItemPetInfoName
            itemPetInfoAgeTextView = binding.textViewItemPetInfoAge
            itemPetInfoSexTextView = binding.textViewItemPetInfoSex
            itemPetInfoCenterTextView = binding.textViewItemPetInfoCenter

            // 동물 클릭시 이벤트
            binding.root.setOnClickListener {
                listener.onItemClick()
            }

        }

        fun bind(petInfo: PetInfo?) {

            val imageUrl: String

            if (petInfo?.thumbnail != null) {
                imageUrl = "https://${petInfo.thumbnail}"
            } else {
                imageUrl = ""
            }

            if (petInfo != null) {
                itemPetInfoNameTextView.text = petInfo.name
                itemPetInfoAgeTextView.text = petInfo.age
                itemPetInfoCenterTextView.text = petInfo.center

                when (petInfo.sex) {
                    "M" -> itemPetInfoSexTextView.text = "수컷"
                    "W" -> itemPetInfoSexTextView.text = "암컷"
                }

                when (petInfo.like) {
                    true -> itemPetLikeImage.setImageResource(R.drawable.ic_full_heart_18dp)
                    false -> itemPetLikeImage.setImageResource(R.drawable.ic_heart_18dp)
                }

            }

            if (imageUrl.isNotEmpty()) {
                Glide.with(itemView.context)
                    .load(imageUrl)
                    .into(itemPetImage)
            } else {
                Glide.with(itemView.context)
                    .load(R.drawable.img_profile)
                    .into(itemPetImage)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {

        val binding = ItemHomePetListBinding.inflate(LayoutInflater.from(parent.context))
        val homeViewHolder = HomeViewHolder(binding)

        binding.root.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        return homeViewHolder
    }

    override fun getItemCount(): Int {
        return allPetsInfoList.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(allPetsInfoList[position])

    }

    fun setList(petInfo: PetInfo) {
        if (!allPetsInfoList.contains(petInfo)) {
            allPetsInfoList.add(petInfo)
        }
    }

}

interface OnItemClickListener {
    fun onItemClick()
}