package com.project.comepethome.ui.find

import androidx.lifecycle.ViewModel
import com.project.comepethome.data.network.repository.FindIdRepository

class FindIdViewModel : ViewModel() {

    private val findIdRepository = FindIdRepository()

    fun findUserId(
        name: String,
        phoneNumber: String,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        findIdRepository.findUserId(name, phoneNumber, onSuccess, onError)
    }
}