package com.project.comepethome.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.comepethome.R
import com.project.comepethome.databinding.FragmentHomeBinding
import com.project.comepethome.ui.main.MainActivity


class HomeFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var binding: FragmentHomeBinding

    lateinit var homeViewModel: HomeViewModel
    lateinit var homeAdapter: HomeAdapter
    private var currentPage = 0

    val TAG = "HomeFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentHomeBinding.inflate(layoutInflater)

        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        homeViewModel.initialGetAllPetInfo(currentPage.toString())

        binding.recyclerViewHome.apply {
            binding.recyclerViewHome.layoutManager = GridLayoutManager(context, 2)
            homeAdapter = HomeAdapter(object : OnItemClickListener{
                override fun onItemClick(petId: Int) {

                    homeViewModel.getPetDetailsInfo(petId)

                    homeViewModel.currentPetDetailsInfo().observe(viewLifecycleOwner) { petInfo ->

                        val bundle = Bundle()
                        bundle.putParcelable("petInfo", petInfo)

                        setFragmentResult("petDetailsInfo", bundle)
                    }

                    mainActivity.addFragment(MainActivity.PET_INFO_FRAGMENT)
                }
            })
            binding.recyclerViewHome.adapter = homeAdapter
        }

        homeViewModel.getCurrentPagePetInfo().observe(viewLifecycleOwner) {
            it.forEach { petInfo ->
                homeAdapter.setList(petInfo)
            }
            homeAdapter.notifyItemRangeChanged(currentPage * 10, 10)
        }


        binding.recyclerViewHome.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastVisibleItemPosition = (recyclerView.layoutManager as GridLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
                val itemTotalCount = recyclerView.adapter!!.itemCount - 1

                if (!binding.recyclerViewHome.canScrollVertically(1) && lastVisibleItemPosition == itemTotalCount) {
                    currentPage++
                    homeViewModel.initialGetAllPetInfo(currentPage.toString())
                }

            }
        })

        initUI()
        moveToSearchAnimal()

        return binding.root
    }

    private fun initUI() {
        binding.run {
            mainActivity.bottomNavigation()
            mainActivity.activityMainBinding.bottomNavigationViewMain.visibility = View.VISIBLE
        }
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