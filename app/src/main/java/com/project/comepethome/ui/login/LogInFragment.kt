package com.project.comepethome.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.project.comepethome.R
import com.project.comepethome.databinding.FragmentLogInBinding
import com.project.comepethome.ui.main.MainActivity

class LogInFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var binding: FragmentLogInBinding

    private var lastBackPressedTime: Long = 0
    private val BACK_PRESS_INTERVAL: Long = 2000

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentLogInBinding.inflate(layoutInflater)

        mainActivity.hideBottomNavigationView()

        moveToLogin()
        moveToFindID()
        moveToFindPassword()
        moveToJoin()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 뒤로가기 처리를 위한 설정
        view.isFocusableInTouchMode = true
        view.requestFocus()
        view.setOnKeyListener { _, keyCode, event ->
            if (keyCode == android.view.KeyEvent.KEYCODE_BACK && event.action == android.view.KeyEvent.ACTION_UP) {
                handleBackPressed()
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }
    }

    private fun handleBackPressed() {
        val currentTime = System.currentTimeMillis()

        if (currentTime - lastBackPressedTime > BACK_PRESS_INTERVAL) {
            lastBackPressedTime = currentTime

            Toast.makeText(requireContext(), "뒤로 가기를 한 번 더 누르면 종료됩니다", Toast.LENGTH_SHORT).show()
        } else {
            // 일정 시간 내에 두 번째 뒤로가기 버튼을 눌렀으므로 앱 종료
            requireActivity().finish()
        }
    }

    private fun moveToLogin() {
        binding.buttonLogin.setOnClickListener {
            mainActivity.replaceFragment(MainActivity.HOME_FRAGMENT, false, null)
            mainActivity.showBottomNavigationView()
            mainActivity.bottomNavigation()
            mainActivity.selectBottomNavigationItem(R.id.home_menu)
        }
    }

    private fun moveToFindID() {
        binding.textViewIdSearchLogin.setOnClickListener {
            mainActivity.replaceFragment(MainActivity.FIND_ID_FRAGMENT, true, null)
        }
    }

    private fun moveToFindPassword() {
        binding.textViewPasswordSearchLogin.setOnClickListener {
            mainActivity.replaceFragment(MainActivity.FIND_PASSWORD_FRAGMENT, true, null)
        }
    }

    private fun moveToJoin() {
        binding.textViewJoinLogin.setOnClickListener {
            mainActivity.replaceFragment(MainActivity.JOIN_ID_FRAGMENT, true, null)
        }
    }

}