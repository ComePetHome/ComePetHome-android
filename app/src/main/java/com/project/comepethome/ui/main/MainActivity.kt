package com.project.comepethome.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.project.comepethome.R
import com.project.comepethome.databinding.ActivityMainBinding
import com.project.comepethome.ui.find.FindIdCompleteFragment
import com.project.comepethome.ui.find.FindIdFragment
import com.project.comepethome.ui.find.FindPasswordCompleteFragment
import com.project.comepethome.ui.find.FindPasswordFragment
import com.project.comepethome.ui.home.HomeFragment
import com.project.comepethome.ui.join.JoinFragment
import com.project.comepethome.ui.login.LogInFragment

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        replaceFragment(LOG_IN_FRAGMENT, false, null)
    }

    fun replaceFragment(name:String, addToBackStack:Boolean, bundle:Bundle?){

        // Fragment 교체 상태로 설정
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        var newFragment: Fragment? = null

        // 새로운 Fragment를 담을 변수
        newFragment = when(name){
            LOG_IN_FRAGMENT -> LogInFragment()
            JOIN_FRAGMENT -> JoinFragment()
            FIND_ID_FRAGMENT -> FindIdFragment()
            FIND_ID_COMPLETE_FRAGMENT -> FindIdCompleteFragment()
            FIND_PASSWORD_FRAGMENT -> FindPasswordFragment()
            FIND_PASSWORD_COMPLETE_FRAGMENT -> FindPasswordCompleteFragment()
            HOME_FRAGMENT -> HomeFragment()

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

    companion object {
        val LOG_IN_FRAGMENT = "LogInFragment"
        val JOIN_FRAGMENT = "JoinFragment"
        val FIND_ID_FRAGMENT = "FindIdFragment"
        val FIND_ID_COMPLETE_FRAGMENT = "FindIdCompleteFragment"
        val FIND_PASSWORD_FRAGMENT = "FindPasswordFragment"
        val FIND_PASSWORD_COMPLETE_FRAGMENT = "FindPasswordCompleteFragment"
        val HOME_FRAGMENT = "HomeFragment"
    }

}