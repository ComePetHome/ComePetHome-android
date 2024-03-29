package com.project.comepethome.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.project.comepethome.R
import com.project.comepethome.databinding.ActivityMainBinding
import com.project.comepethome.databinding.ItemUserLoginBinding
import com.project.comepethome.databinding.ItemUserWithdrawBinding
import com.project.comepethome.ui.board.BoardInfoFragment
import com.project.comepethome.ui.board.BoardMainFragment
import com.project.comepethome.ui.board.BoardSearchFragment
import com.project.comepethome.ui.board.BoardWriteFragment
import com.project.comepethome.ui.find.FindIdCompleteFragment
import com.project.comepethome.ui.find.FindIdFragment
import com.project.comepethome.ui.find.ChangePasswordFragment
import com.project.comepethome.ui.find.FindPasswordFragment
import com.project.comepethome.ui.home.HomeFragment
import com.project.comepethome.ui.home.PetInfoFragment
import com.project.comepethome.ui.home.PetInfoVideoFragment
import com.project.comepethome.ui.join.JoinIDFragment
import com.project.comepethome.ui.join.JoinNameFragment
import com.project.comepethome.ui.join.JoinNicknameFragment
import com.project.comepethome.ui.join.JoinPasswordFragment
import com.project.comepethome.ui.join.JoinPhoneNumberFragment
import com.project.comepethome.ui.login.LogInFragment
import com.project.comepethome.ui.mypage.MyPageBoardFragment
import com.project.comepethome.ui.mypage.MyPageFragment
import com.project.comepethome.ui.mypage.MyPageLikeAnimalFragment
import com.project.comepethome.ui.mypage.MyPageModifyFragment
import com.project.comepethome.ui.search.SearchAnimalFragment

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    private var lastBackPressedTime: Long = 0
    private val BACK_PRESS_INTERVAL: Long = 2000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        replaceFragment(HOME_FRAGMENT, false, null)
    }

    override fun onBackPressed() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView_main)

        when (currentFragment) {
            is HomeFragment, is BoardMainFragment, is SearchAnimalFragment, is MyPageFragment -> handleBackPressed()
            else -> super.onBackPressed()
        }
    }

    private fun handleBackPressed() {
        val currentTime = System.currentTimeMillis()

        if (currentTime - lastBackPressedTime > BACK_PRESS_INTERVAL) {
            lastBackPressedTime = currentTime

            Toast.makeText(this, "뒤로 가기를 한 번 더 누르면 종료됩니다", Toast.LENGTH_SHORT).show()
        } else {
            // 해당 Fragment에서 두 번의 뒤로 가기 버튼이 BACK_PRESS_INTERVAL 안에 눌렸으므로 앱을 종료
            finish()
        }
    }

    fun addFragment(name:String){

        // Fragment 교체 상태로 설정
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        var newFragment: Fragment? = null

        // 새로운 Fragment를 담을 변수
        newFragment = when(name){
            LOG_IN_FRAGMENT -> LogInFragment()
            JOIN_ID_FRAGMENT -> JoinIDFragment()
            JOIN_PASSWORD_FRAGMENT -> JoinPasswordFragment()
            JOIN_NICKNAME_FRAGMENT -> JoinNicknameFragment()
            JOIN_NAME_FRAGMENT -> JoinNameFragment()
            JOIN_PHONE_NUMBER_FRAGMENT -> JoinPhoneNumberFragment()
            FIND_ID_FRAGMENT -> FindIdFragment()
            FIND_ID_COMPLETE_FRAGMENT -> FindIdCompleteFragment()
            FIND_PASSWORD_FRAGMENT -> FindPasswordFragment()
            CHANGE_PASSWORD_FRAGMENT -> ChangePasswordFragment()
            HOME_FRAGMENT -> HomeFragment()
            BOARD_MAIN_FRAGMENT -> BoardMainFragment()
            BOARD_INFO_FRAGMENT -> BoardInfoFragment()
            BOARD_WRITE_FRAGMENT -> BoardWriteFragment()
            BOARD_SEARCH_FRAGMENT -> BoardSearchFragment()
            SEARCH_ANIMAL_FRAGMENT -> SearchAnimalFragment()
            MYPAGE_FRAGMENT -> MyPageFragment()
            MYPAGE_MODIFY_FRAGMENT -> MyPageModifyFragment()
            MYPAGE_LIKE_ANIMAL_FRAGMENT -> MyPageLikeAnimalFragment()
            MYPAGE_BOARD_FRAGMENT -> MyPageBoardFragment()
            PET_INFO_FRAGMENT -> PetInfoFragment()
            PET_INFO_VIDEO_FRAGMENT -> PetInfoVideoFragment()

            else -> Fragment()
        }

        if(newFragment != null) {

            // Fragment를 Backstack에 넣어 이전으로 돌아가는 기능이 동작할 수 있도록 한다.
            fragmentTransaction.addToBackStack(name)

            // Fragment를 추가한다.
            fragmentTransaction.add(R.id.fragmentContainerView_main, newFragment)

            // 교체 명령이 동작하도록 한다.
            fragmentTransaction.commit()
        }
    }

    fun replaceFragment(name:String, addToBackStack:Boolean, bundle:Bundle?){

        // Fragment 교체 상태로 설정
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        var newFragment: Fragment? = null

        // 새로운 Fragment를 담을 변수
        newFragment = when(name){
            LOG_IN_FRAGMENT -> LogInFragment()
            JOIN_ID_FRAGMENT -> JoinIDFragment()
            JOIN_PASSWORD_FRAGMENT -> JoinPasswordFragment()
            JOIN_NICKNAME_FRAGMENT -> JoinNicknameFragment()
            JOIN_NAME_FRAGMENT -> JoinNameFragment()
            JOIN_PHONE_NUMBER_FRAGMENT -> JoinPhoneNumberFragment()
            FIND_ID_FRAGMENT -> FindIdFragment()
            FIND_ID_COMPLETE_FRAGMENT -> FindIdCompleteFragment()
            FIND_PASSWORD_FRAGMENT -> FindPasswordFragment()
            CHANGE_PASSWORD_FRAGMENT -> ChangePasswordFragment()
            HOME_FRAGMENT -> HomeFragment()
            BOARD_MAIN_FRAGMENT -> BoardMainFragment()
            BOARD_INFO_FRAGMENT -> BoardInfoFragment()
            BOARD_WRITE_FRAGMENT -> BoardWriteFragment()
            BOARD_SEARCH_FRAGMENT -> BoardSearchFragment()
            SEARCH_ANIMAL_FRAGMENT -> SearchAnimalFragment()
            MYPAGE_FRAGMENT -> MyPageFragment()
            MYPAGE_MODIFY_FRAGMENT -> MyPageModifyFragment()
            MYPAGE_LIKE_ANIMAL_FRAGMENT -> MyPageLikeAnimalFragment()
            MYPAGE_BOARD_FRAGMENT -> MyPageBoardFragment()
            PET_INFO_FRAGMENT -> PetInfoFragment()
            PET_INFO_VIDEO_FRAGMENT -> PetInfoVideoFragment()

            else -> Fragment()
        }

        newFragment?.arguments = bundle

        if(newFragment != null) {

            // Fragment를 교채한다.
            fragmentTransaction.replace(R.id.fragmentContainerView_main, newFragment)

            if (addToBackStack == true) {
                // Fragment를 Backstack에 넣어 이전으로 돌아가는 기능이 동작할 수 있도록 한다.
                fragmentTransaction.addToBackStack(name)
            }

            // 교체 명령이 동작하도록 한다.
            fragmentTransaction.commit()
        }
    }

    // Fragment를 BackStack에서 제거한다.
    fun removeFragment(name: String){
        supportFragmentManager.popBackStack(name, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    fun showBottomNavigationView() {
        activityMainBinding.bottomNavigationViewMain.visibility = View.VISIBLE
    }

    fun hideBottomNavigationView() {
        activityMainBinding.bottomNavigationViewMain.visibility = View.GONE
    }

    fun bottomNavigation() {

        activityMainBinding.bottomNavigationViewMain.run {

            setOnItemSelectedListener {
                when(it.itemId) {
                    R.id.home_menu -> {
                        removeAllBackStack()
                        replaceFragment(HOME_FRAGMENT, false, null)
                        return@setOnItemSelectedListener true
                    }
                    R.id.board_menu -> {
                        removeAllBackStack()
                        replaceFragment(BOARD_MAIN_FRAGMENT, false, null)
                        return@setOnItemSelectedListener true
                    }
                    R.id.search_menu -> {
                        removeAllBackStack()
                        replaceFragment(SEARCH_ANIMAL_FRAGMENT, false, null)
                        return@setOnItemSelectedListener true
                    }
                    R.id.my_menu -> {
                        removeAllBackStack()
                        replaceFragment(MYPAGE_FRAGMENT, false, null)
                        return@setOnItemSelectedListener true
                    }

                    else -> return@setOnItemSelectedListener false
                }
            }
        }
    }

    fun selectBottomNavigationItem(itemId: Int) {
        activityMainBinding.bottomNavigationViewMain.selectedItemId = itemId
    }

    fun removeAllBackStack() {
        val fragmentManager = supportFragmentManager
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    fun showSnackbar(message: String) {
        val snackbar = Snackbar.make(activityMainBinding.root ,message, Snackbar.LENGTH_SHORT)
        snackbar.view.elevation = 0f
        snackbar.show()
    }

    fun loginAlert() {
        val itemUserLoginBinding = ItemUserLoginBinding.inflate(layoutInflater)
        val builder = MaterialAlertDialogBuilder(this)
        builder.setView(itemUserLoginBinding.root)
        val dialog = builder.create()

        itemUserLoginBinding.buttonLogin.setOnClickListener {
            addFragment(LOG_IN_FRAGMENT)
            dialog.dismiss()
        }

        itemUserLoginBinding.buttonCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    companion object {

        var isLogIn = false

        var accessToken: String? = null
        var refreshToken: String? = null

        var loginId = ""
        var loginPassword = ""

        val LOG_IN_FRAGMENT = "LogInFragment"
        val JOIN_ID_FRAGMENT = "JoinIDFragment"
        val JOIN_PASSWORD_FRAGMENT = "JoinPasswordFragment"
        val JOIN_NICKNAME_FRAGMENT = "JoinNicknameFragment"
        val JOIN_NAME_FRAGMENT = "JoinNameFragment"
        val JOIN_PHONE_NUMBER_FRAGMENT = "JoinPhoneNumberFragment"
        val FIND_ID_FRAGMENT = "FindIdFragment"
        val FIND_ID_COMPLETE_FRAGMENT = "FindIdCompleteFragment"
        val FIND_PASSWORD_FRAGMENT = "FindPasswordFragment"
        val CHANGE_PASSWORD_FRAGMENT = "ChangePasswordFragment"
        val HOME_FRAGMENT = "HomeFragment"
        val BOARD_MAIN_FRAGMENT = "BoardMainFragment"
        val BOARD_INFO_FRAGMENT = "BoardInfoFragment"
        val BOARD_WRITE_FRAGMENT = "BoardWriteFragment"
        val BOARD_SEARCH_FRAGMENT = "BoardSearchFragment"
        val SEARCH_ANIMAL_FRAGMENT = "SearchAnimalFragment"
        val MYPAGE_FRAGMENT = "MyPageFragment"
        val MYPAGE_MODIFY_FRAGMENT = "MyPageModifyFragment"
        val MYPAGE_LIKE_ANIMAL_FRAGMENT = "MyPageLikeAnimalFragment"
        val MYPAGE_BOARD_FRAGMENT = "MyPageBoardFragment"
        val PET_INFO_FRAGMENT = "PetInfoFragment"
        val PET_INFO_VIDEO_FRAGMENT = "PetInfoVideoFragment"
    }

}