package com.project.comepethome.ui.home

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Align
import com.naver.maps.map.overlay.InfoWindow
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage
import com.project.comepethome.R
import com.project.comepethome.data.model.PetDetailsInfo
import com.project.comepethome.databinding.FragmentPetInfoBinding
import com.project.comepethome.ui.main.MainActivity

class PetInfoFragment : Fragment(), OnMapReadyCallback {

    lateinit var mainActivity: MainActivity
    lateinit var binding: FragmentPetInfoBinding

    private lateinit var naverMap: NaverMap

    lateinit var homeViewModel: HomeViewModel

    private var isLiked = false
    private var petId = 0
    private var isMapOpen = false
    private var petName: String = ""
    private var videoLink: String = ""

    var currentPetDetailsInfo = PetDetailsInfo()

    val TAG = "PetInfoFragment"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentPetInfoBinding.inflate(layoutInflater)

        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        setFragmentResultListener("petDetailsInfo") { _, bundle ->

            currentPetDetailsInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getParcelable("petInfo", PetDetailsInfo::class.java)!!
            }else {
                bundle.getParcelable("petInfo")!!
            }

            isLiked = bundle.getBoolean("petLike")
            petId = bundle.getInt("petId")

            settingPetLike(isLiked)

            settingPetInfo(currentPetDetailsInfo)

        }

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

                    if (MainActivity.isLogIn) {
                        when(it.itemId) {
                            R.id.item_like -> {
                                isLiked = !isLiked
                                updateLikeIcon()
                            }
                        }

                    } else {
                        mainActivity.loginAlert()
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

                    if (videoLink.isNotEmpty()) {
                        val bundle = Bundle()
                        bundle.putString("petName", petName)
                        bundle.putString("videoLink", videoLink)

                        setFragmentResult("petNameAndVideo", bundle)

                        mainActivity.addFragment(MainActivity.PET_INFO_VIDEO_FRAGMENT)
                    } else {
                        mainActivity.showSnackbar("동영상이 없습니다 ㅠㅠ")
                    }

                }

            }
        }
    }


    private fun updateLikeIcon() {
        if (isLiked) {
            homeViewModel.likeAnimals("${MainActivity.accessToken}",petId)
            binding.materialToolbarPetInfo.menu.findItem(R.id.item_like).icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_full_heart_18dp)
        } else {
            homeViewModel.unLikeAnimals("${MainActivity.accessToken}",petId)
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

    private fun settingPetLike(like : Boolean) {
        if (like) {
            binding.materialToolbarPetInfo.menu.findItem(R.id.item_like).icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_full_heart_18dp)
        } else {
            binding.materialToolbarPetInfo.menu.findItem(R.id.item_like).icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_heart_18dp)
        }
    }

    private fun settingPetInfo(petDetailsInfo: PetDetailsInfo) {

        binding.materialToolbarPetInfo.title = petDetailsInfo.name

        when(petDetailsInfo.species) {
            "DOG" -> binding.buttonAnimalTypePetInfo.text = "개"
            "CAT" -> binding.buttonAnimalTypePetInfo.text = "고양이"
        }

        binding.buttonAnimalBreedPetInfo.text = petDetailsInfo.breeds

        // 문자열로 부터 숫자 출력하는 과정
        val ageString = petDetailsInfo.age
        val ageRegex = """(\d+)""".toRegex()
        val matchResult = ageRegex.find(ageString)
        val age: Int? = matchResult?.groupValues?.get(1)?.toIntOrNull()

        binding.buttonAnimalAgePetInfo.text =  "${age}살"
        binding.buttonAnimalWeightPetInfo.text = petDetailsInfo.weight.toString() + "kg"

        // 문자열에서 날짜 부분 추출
        // ex) petDetailsInfo.enlistment_date : 2023-12-21T09:00:00.000Z
        val dateParts = petDetailsInfo.enlistment_date.split("T")
        val admissionDate = dateParts[0] // "T"를 기준으로 앞 부분 선택
        binding.textAdmissionDatePetInfo.text = admissionDate
        binding.textCenterNamePetInfo.text = petDetailsInfo.center
        binding.textViewIntroductionPetInfo.text = petDetailsInfo.intro_contents

        petName = petDetailsInfo.name
        videoLink = petDetailsInfo.intro_url
    }

}