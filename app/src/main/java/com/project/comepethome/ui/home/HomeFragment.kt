package com.project.comepethome.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Space
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.comepethome.R
import com.project.comepethome.databinding.FragmentHomeBinding
import com.project.comepethome.ui.main.MainActivity


class HomeFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentHomeBinding.inflate(layoutInflater)

        mainActivity.bottomNavigation()

        initRecyclerView()
        moveToSearchAnimal()

        return binding.root
    }

    private fun initRecyclerView() {
        val recyclerView = binding.recyclerViewHome
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = HomeAdapter(mainActivity)

    }

    private fun moveToSearchAnimal() {
        binding.materialToolbarHome.run {
            setOnMenuItemClickListener {
                when(it.itemId) {
                    R.id.item_search -> {
                        mainActivity.replaceFragment(MainActivity.SEARCH_ANIMAL_FRAGMENT, false, null)
                        mainActivity.selectBottomNavigationItem(R.id.search_menu)
                    }
                }
                false
            }
        }
    }

}