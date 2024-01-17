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
}