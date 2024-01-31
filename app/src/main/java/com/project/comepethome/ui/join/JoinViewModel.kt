package com.project.comepethome.ui.join

import androidx.lifecycle.ViewModel
import com.project.comepethome.data.network.repository.JoinRepository

class JoinViewModel : ViewModel() {

    private val joinRepository = JoinRepository()

    fun joinUser(
        joinId: String,
        joinPassword: String,
        joinNickname: String,
        joinName: String,
        joinPhoneNumber: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        joinRepository.joinUser(joinId, joinPassword, joinNickname, joinName, joinPhoneNumber, onSuccess, onError)
    }

    fun checkUserId(
        userId: String,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        joinRepository.checkUserId(userId, onSuccess, onError)
    }

    fun sendEmail(userId: String) {
        joinRepository.sendEmail(userId)
    }

    fun certificationEmail(
        userId: String,
        code: String,
        onSuccess: (String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        joinRepository.certificationEmail(userId, code, onSuccess, onFailure)
    }

    fun verificationEmail(
        userId: String,
        code: String,
        onSuccess: (String) -> Unit,
        onFailure: (String) -> Unit
    ){
        joinRepository.verificationEmail(userId, code, onSuccess, onFailure)
    }

}