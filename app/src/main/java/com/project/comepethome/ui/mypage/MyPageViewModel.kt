package com.project.comepethome.ui.mypage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.comepethome.data.model.UserProfileResponse
import com.project.comepethome.data.network.repository.MyPageRepository

class MyPageViewModel : ViewModel() {

    private val myPageRepository = MyPageRepository()

    private val _userProfileLiveData = MutableLiveData<UserProfileResponse>()
    private val _userProfileImgLiveData = MutableLiveData<String?>()
    val userProfileLiveData: LiveData<UserProfileResponse>
        get() = _userProfileLiveData

    val userProfileImgLiveData: MutableLiveData<String?>
        get() = _userProfileImgLiveData

    fun getUserProfile(accessToken: String) {
        myPageRepository.getUserProfile(
            accessToken,
            onSuccess = { userProfile ->
                _userProfileLiveData.value = userProfile
            },
            onFailure = { errorMessage ->
                refreshAccessToken()
            }
        )
    }

    fun refreshAccessToken() {
        myPageRepository.refreshAccessToken(
            onSuccess = { userProfile ->
                _userProfileLiveData.value = userProfile
            },
            onFailure = { errorMessage ->
            }
        )
    }

    fun getUserProfileImg(accessToken: String) {
        myPageRepository.getUserProfileImg(
            accessToken,
            onSuccess = { userProfileImg ->
                _userProfileImgLiveData.value = userProfileImg
            },
            onFailure = {
                _userProfileImgLiveData.value = null
            }
        )
    }

}