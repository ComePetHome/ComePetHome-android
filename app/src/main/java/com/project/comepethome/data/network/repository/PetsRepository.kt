package com.project.comepethome.data.network.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.project.comepethome.data.model.PetDetailsInfo
import com.project.comepethome.data.model.PetInfo
import com.project.comepethome.data.network.api.ComePetHomeAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

class PetsRepository {

    private val comePetHomeAPI: ComePetHomeAPI
    var _currentPagePetInfoLiveData = MutableLiveData<List<PetInfo>>()
    var _currentPetDetailsInfoLiveData = MutableLiveData<PetDetailsInfo>()

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://54.180.142.14:9001/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        comePetHomeAPI = retrofit.create(ComePetHomeAPI::class.java)
    }

    fun initialGetAllPetInfo(pageNumber: String) {
        val call = comePetHomeAPI.initialGetAllPetInfo(pageNumber)

        call.enqueue(object : Callback<List<PetInfo>> {
            override fun onResponse(call: Call<List<PetInfo>>, response: Response<List<PetInfo>>) {
                if (response.isSuccessful) {
                    _currentPagePetInfoLiveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<PetInfo>>, t: Throwable) {
                Log.d("HomeFragment", "네트워크 오류: ${t.message}")
            }

        })

    }

    fun getPetDetailsInfo(petId: Int) {
        val call = comePetHomeAPI.getPetDetailsInfo(petId)

        call.enqueue(object : Callback<PetDetailsInfo> {
            override fun onResponse(call: Call<PetDetailsInfo>, response: Response<PetDetailsInfo>) {
                if (response.isSuccessful) {
                    _currentPetDetailsInfoLiveData.value = response.body()

                }

            }

            override fun onFailure(call: Call<PetDetailsInfo>, t: Throwable) {
                Log.d("HomeFragment", "네트워크 오류: ${t.message}")
            }

        })

    }

}