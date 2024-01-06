package com.project.comepethome.ui.mypage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.project.comepethome.R
import com.project.comepethome.databinding.FragmentMyPageLikeAnimalBinding
import com.project.comepethome.ui.main.MainActivity

class MyPageLikeAnimalFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var binding: FragmentMyPageLikeAnimalBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentMyPageLikeAnimalBinding.inflate(layoutInflater)

        initUi()

        return binding.root
    }

    private fun initUi() {
        binding.run {
            materialToolbarMyPageLikeAnimal.run {
                setNavigationIcon(R.drawable.ic_back_24dp)
                setNavigationOnClickListener {
                    mainActivity.removeFragment(MainActivity.MYPAGE_LIKE_ANIMAL_FRAGMENT)
                }
            }

            recyclerViewMyPageLikeAnimal.run {
                layoutManager = GridLayoutManager(context, 2)
                adapter = MyPageLikeAnimalAdapter()
            }

        }
    }

}