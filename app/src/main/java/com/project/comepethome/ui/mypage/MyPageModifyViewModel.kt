package com.project.comepethome.ui.mypage

import androidx.lifecycle.ViewModel
import com.project.comepethome.data.network.repository.MyPageModifyRepository

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

}