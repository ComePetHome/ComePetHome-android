package com.project.comepethome.ui.login

import androidx.lifecycle.ViewModel
import com.project.comepethome.data.network.repository.LogInRepository
class LogInViewModel : ViewModel() {

    private val logInRepository = LogInRepository()

    fun loginUser(
        loginId: String,
        loginPassword: String,
        onSuccess: (accessToken: String?, refreshToken: String?) -> Unit,
        onError: (String) -> Unit
    ){
        logInRepository.loginUser(loginId, loginPassword, onSuccess, onError)
    }

}