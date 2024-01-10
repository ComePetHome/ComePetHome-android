package com.project.comepethome.ui.mypage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.project.comepethome.R
import com.project.comepethome.databinding.FragmentMyPageModifyBinding
import com.project.comepethome.databinding.ItemUserWithdrawBinding
import com.project.comepethome.ui.main.MainActivity

class MyPageModifyFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var binding: FragmentMyPageModifyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentMyPageModifyBinding.inflate(layoutInflater)

        initUi()

        return binding.root
    }

    private fun initUi() {
        binding.run {
            materialToolbarMyPageModify.run {
                setNavigationIcon(R.drawable.ic_back_24dp)
                setNavigationOnClickListener {
                    mainActivity.removeFragment(MainActivity.MYPAGE_MODIFY_FRAGMENT)
                }
            }

            // 로그아웃
            textViewLogoutMyPageModify.setOnClickListener {
                mainActivity.replaceFragment(MainActivity.LOG_IN_FRAGMENT, false, null)
                Snackbar.make(binding.root, "로그아웃 되었습니다.", Snackbar.LENGTH_SHORT).show()
            }

            // 회원탈퇴
            textViewWithdrawalMyPageModify.setOnClickListener {
                val itemUserWithdrawBinding = ItemUserWithdrawBinding.inflate(layoutInflater)
                val builder = MaterialAlertDialogBuilder(mainActivity)
                builder.setView(itemUserWithdrawBinding.root)
                val dialog = builder.create()

                itemUserWithdrawBinding.buttonCancel.setOnClickListener {
                    dialog.dismiss()
                }

                itemUserWithdrawBinding.buttonWithdraw.setOnClickListener {
                    dialog.dismiss()
                    mainActivity.removeFragment(MainActivity.MYPAGE_MODIFY_FRAGMENT)
                    mainActivity.replaceFragment(MainActivity.LOG_IN_FRAGMENT, false, null)
                    Snackbar.make(binding.root, "회원탈퇴 되었습니다.", Snackbar.LENGTH_SHORT).show()
                }

                dialog.show()
            }

        }
    }

}