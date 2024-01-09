package com.project.comepethome.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.project.comepethome.R
import com.project.comepethome.databinding.FragmentPetInfoBinding
import com.project.comepethome.ui.main.MainActivity

class PetInfoFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var binding: FragmentPetInfoBinding

    private var isLiked = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentPetInfoBinding.inflate(layoutInflater)

        initUI()

        return binding.root
    }

    private fun initUI() {
        binding.run {
            materialToolbarPetInfo.run {
                setNavigationIcon(R.drawable.ic_back_24dp)
                setNavigationOnClickListener {
                    mainActivity.removeFragment(MainActivity.PET_INFO_FRAGMENT)
                }

                // 좋아요 클릭시
                setOnMenuItemClickListener {
                    when(it.itemId) {
                        R.id.item_like -> {
                            isLiked = !isLiked
                            updateLikeIcon()
                        }
                    }
                    false
                }
            }
        }
    }


    private fun updateLikeIcon() {
        if (isLiked) {
            binding.materialToolbarPetInfo.menu.findItem(R.id.item_like).icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_full_heart_18dp)
        } else {
            binding.materialToolbarPetInfo.menu.findItem(R.id.item_like).icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_heart_18dp)
        }
    }

}