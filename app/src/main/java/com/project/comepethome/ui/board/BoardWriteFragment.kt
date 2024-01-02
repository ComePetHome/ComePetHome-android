package com.project.comepethome.ui.board

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.project.comepethome.R
import com.project.comepethome.databinding.FragmentBoardWriteBinding
import com.project.comepethome.ui.main.MainActivity

class BoardWriteFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var binding: FragmentBoardWriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentBoardWriteBinding.inflate(layoutInflater)

        initMaterialToolbar()
        initUI()
        writeButton()

        return binding.root
    }

    private fun initMaterialToolbar() {
        binding.materialToolbarBoardWrite.run {
            setNavigationIcon(R.drawable.ic_x_16dp)
            setNavigationOnClickListener {
                mainActivity.removeFragment(MainActivity.BOARD_WRITE_FRAGMENT)
            }
        }
    }

    private fun initUI() {
        binding.run {
            val category = resources.getStringArray(R.array.array_board_category)

            spinnerCategoryBoardWrite.run {
                adapter = SpinnerAdapter(mainActivity, R.layout.item_spinner, category)
            }
        }

    }

    private fun writeButton() {
        binding.buttonBoardWrite.setOnClickListener {
            mainActivity.removeFragment(MainActivity.BOARD_WRITE_FRAGMENT)
        }
    }

}