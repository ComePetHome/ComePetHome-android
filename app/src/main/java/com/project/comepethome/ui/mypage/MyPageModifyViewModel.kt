package com.project.comepethome.ui.mypage

import androidx.lifecycle.ViewModel
import com.project.comepethome.data.network.repository.MyPageModifyRepository
import okhttp3.MultipartBody

class MyPageModifyViewModel: ViewModel() {

    private val myPageModifyRepository = MyPageModifyRepository()

    fun modifyUserProfile(
        accessToken: String,
        nickName: String,
        name: String,
        phoneNumber: String
    ) {
        myPageModifyRepository.modifyUserProfile(
            accessToken,
            nickName,
            name,
            phoneNumber
        )
    }

    fun uploadProfileImg(
        accessToken: String,
        imagePart: MultipartBody.Part,
    ) {
        myPageModifyRepository.uploadProfileImg(accessToken, imagePart)
    }

    fun updateProfileImg(
        accessToken: String,
        imagePart: MultipartBody.Part,
    ) {
        myPageModifyRepository.updateProfileImg(accessToken, imagePart)
    }

}