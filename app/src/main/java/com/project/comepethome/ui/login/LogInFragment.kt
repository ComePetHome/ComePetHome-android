package com.project.comepethome.ui.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.project.comepethome.R
import com.project.comepethome.databinding.FragmentLogInBinding
import com.project.comepethome.ui.main.MainActivity

class LogInFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var binding: FragmentLogInBinding

    lateinit var viewModel: LogInViewModel

    val TAG = "LogInFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentLogInBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(this)[LogInViewModel::class.java]

        mainActivity.hideBottomNavigationView()

        moveToLogin()
        moveToFindID()
        moveToFindPassword()
        moveToJoin()

        return binding.root
    }

    private fun moveToLogin() {
        binding.buttonLogin.setOnClickListener {

            MainActivity.loginId = binding.editTextIdLogin.text.toString()
            MainActivity.loginPassword = binding.editTextPasswordLogin.text.toString()

            viewModel.loginUser(MainActivity.loginId,
                MainActivity.loginPassword,
                { accessToken, refreshToken ->

                    MainActivity.isLogIn = true
                    mainActivity.replaceFragment(MainActivity.HOME_FRAGMENT, false, null)
                    mainActivity.showBottomNavigationView()
                    mainActivity.bottomNavigation()
                    mainActivity.selectBottomNavigationItem(R.id.home_menu)
                },
                { errorMessage ->
                    mainActivity.showSnackbar("아이디 혹은 비밀번호를 잘못 입력하였습니다.")
                }

            )

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