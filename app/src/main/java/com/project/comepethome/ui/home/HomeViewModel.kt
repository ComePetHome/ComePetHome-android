package com.project.comepethome.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.project.comepethome.data.model.PetDetailsInfo
import com.project.comepethome.data.model.PetInfo
import com.project.comepethome.data.network.repository.PetsRepository

class HomeViewModel : ViewModel() {

    private val petsRepository = PetsRepository()

    private val currentPagePetInfoLiveData: LiveData<List<PetInfo>>
        get() = petsRepository._currentPagePetInfoLiveData

    private val currentPetDetailsInfoLiveData: LiveData<PetDetailsInfo>
        get() = petsRepository._currentPetDetailsInfoLiveData

    fun initialGetAllPetInfo(pageNumber: String) {
        petsRepository.initialGetAllPetInfo(pageNumber)
    }

    fun getCurrentPagePetInfo(): LiveData<List<PetInfo>> {
        return currentPagePetInfoLiveData
    }

    fun getPetDetailsInfo(petId: Int) {
        petsRepository.getPetDetailsInfo(petId)
    }

    fun currentPetDetailsInfo() : LiveData<PetDetailsInfo> {
        return currentPetDetailsInfoLiveData
    }

}