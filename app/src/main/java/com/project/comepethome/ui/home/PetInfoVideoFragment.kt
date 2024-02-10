package com.project.comepethome.ui.home

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.fragment.app.setFragmentResultListener
import com.project.comepethome.R
import com.project.comepethome.data.model.PetDetailsInfo
import com.project.comepethome.databinding.FragmentPetInfoVideoBinding
import com.project.comepethome.ui.main.MainActivity

class PetInfoVideoFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var binding: FragmentPetInfoVideoBinding

    private var petName: String = ""
    private var videoLink: String = ""

    val TAG = "PetInfoVideoFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentPetInfoVideoBinding.inflate(layoutInflater)

        setFragmentResultListener("petNameAndVideo") { _, bundle ->

            petName = bundle.getString("petName").toString()
            videoLink = bundle.getString("videoLink").toString()

            binding.materialToolbarPetInfoVideo.title = petName

            binding.webViewPetInfoVideo.run {
                loadUrl(videoLink)
                settings.javaScriptEnabled = true
                webViewClient = WebViewClient()
            }
        }

        initUI()

        return binding.root
    }

    private fun initUI() {
        binding.run {

            mainActivity.hideBottomNavigationView()

            materialToolbarPetInfoVideo.run {
                setNavigationIcon(R.drawable.ic_back_24dp)
                setNavigationOnClickListener {
                    mainActivity.activityMainBinding.bottomNavigationViewMain.visibility = View.VISIBLE
                    mainActivity.removeFragment(MainActivity.PET_INFO_VIDEO_FRAGMENT)
                }
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()

        mainActivity.showBottomNavigationView()
    }

}