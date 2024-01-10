package com.project.comepethome.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Align
import com.naver.maps.map.overlay.InfoWindow
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage
import com.project.comepethome.R
import com.project.comepethome.databinding.FragmentPetInfoBinding
import com.project.comepethome.ui.main.MainActivity

class PetInfoFragment : Fragment(), OnMapReadyCallback {

    lateinit var mainActivity: MainActivity
    lateinit var binding: FragmentPetInfoBinding

    private lateinit var naverMap: NaverMap

    private var isLiked = false
    private var isMapOpen = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentPetInfoBinding.inflate(layoutInflater)

        initUI()

        return binding.root
    }

    private fun initUI() {
        binding.run {

            mainActivity.showBottomNavigationView()

            materialToolbarPetInfo.run {
                setNavigationIcon(R.drawable.ic_back_24dp)
                setNavigationOnClickListener {
                    mainActivity.removeFragment(MainActivity.PET_INFO_FRAGMENT)
                }

                // 좋아요 클릭시
                setOnMenuItemClickListener {
                    when(it.itemId) {
                        R.id.item_like -> {
                            isLiked = !isLiked
                            updateLikeIcon()
                        }
                    }
                    false
                }

                // 지도보기 클릭시
                layoutMapPetInfo.setOnClickListener {
                    isMapOpen = !isMapOpen
                    mapState()
                }

                // 동물 영상 보기 클릭시
                buttonVideoPetInfo.setOnClickListener {
                    mainActivity.replaceFragment(MainActivity.PET_INFO_VIDEO_FRAGMENT, true, null)
                }

            }
        }
    }


    private fun updateLikeIcon() {
        if (isLiked) {
            binding.materialToolbarPetInfo.menu.findItem(R.id.item_like).icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_full_heart_18dp)
        } else {
            binding.materialToolbarPetInfo.menu.findItem(R.id.item_like).icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_heart_18dp)
        }
    }

    private fun mapState() {
        if (isMapOpen) {
            binding.textViewMapStatusPetInfo.text = "지도접기"
            binding.imageMapStatusPetInfo.setImageResource(R.drawable.ic_close)
            binding.layoutCenterLocationInfoPetInfo.visibility = View.VISIBLE
            initMapView()
        } else {
            binding.textViewMapStatusPetInfo.text = "지도보기"
            binding.imageMapStatusPetInfo.setImageResource(R.drawable.ic_open)
            binding.layoutCenterLocationInfoPetInfo.visibility = View.GONE
        }

    }

    private fun initMapView() {
        val fm = childFragmentManager
        val mapFragment = fm.findFragmentById(R.id.map_center_location_pet_info) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.map_center_location_pet_info, it).commit()
            }

        // fragment의 getMapAsync() 메서드로 OnMapReadyCallback 콜백을 등록하면 비동기로 NaverMap 객체를 얻을 수 있다.
        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(naverMap: NaverMap) {
        this.naverMap = naverMap

        val marker = Marker()
        marker.position = LatLng(37.5670135, 126.9783740)
        marker.map = naverMap
        marker.icon = OverlayImage.fromResource(R.drawable.ic_map_icon_24dp)
        marker.setCaptionAligns(Align.Top)

        val infoWindow = InfoWindow()
        infoWindow.adapter = object : InfoWindow.DefaultTextAdapter(requireContext()) {
            override fun getText(infoWindow: InfoWindow): CharSequence {
                return "노원 댕댕 하우스"
            }
        }

        infoWindow.open(marker)
    }

}