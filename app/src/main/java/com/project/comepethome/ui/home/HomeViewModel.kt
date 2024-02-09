package com.project.comepethome.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.project.comepethome.data.model.PetInfo
import com.project.comepethome.data.network.repository.PetsRepository

class HomeViewModel : ViewModel() {

    private val petsRepository = PetsRepository()

    private val currentPagePetInfoLiveData: LiveData<List<PetInfo>>
        get() = petsRepository._currentPagePetInfoLiveData

    fun initialGetAllPetInfo(pageNumber: String) {
        petsRepository.initialGetAllPetInfo(pageNumber)
    }

    fun getCurrentPagePetInfo(): LiveData<List<PetInfo>> {
        return currentPagePetInfoLiveData
    }

}