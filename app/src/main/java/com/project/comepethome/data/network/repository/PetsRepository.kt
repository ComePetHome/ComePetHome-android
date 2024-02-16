package com.project.comepethome.data.network.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.project.comepethome.data.model.PetDetailsInfo
import com.project.comepethome.data.model.PetInfo
import com.project.comepethome.data.model.PetInfoResponse
import com.project.comepethome.data.network.api.ComePetHomeAPI
import com.project.comepethome.ui.main.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

class PetsRepository {

    private val comePetHomeAPI: ComePetHomeAPI
    private val logInRepository = LogInRepository()
    var _currentPagePetInfoLiveData = MutableLiveData<List<PetInfo>>()
    var _currentUserPagePetInfoLiveData = MutableLiveData<List<PetInfo>>()
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

    fun getAllPetInfo(pageNumber: String, accessToken: String) {
        val call = comePetHomeAPI.getAllPetInfo(pageNumber, accessToken)

        call.enqueue(object : Callback<List<PetInfo>> {
            override fun onResponse(call: Call<List<PetInfo>>, response: Response<List<PetInfo>>) {
                if (response.isSuccessful) {
                    _currentUserPagePetInfoLiveData.value = response.body()
                } else{
                    val errorBody = response.errorBody()?.string()

                    if (errorBody?.contains("\"code\":172") == true) {
                        refreshGetAllPetInfo(pageNumber)
                    }
                }
            }

            override fun onFailure(call: Call<List<PetInfo>>, t: Throwable) {
                Log.d("HomeFragment", "네트워크 오류: ${t.message}")
            }

        })

    }

    fun refreshGetAllPetInfo(pageNumber: String) {
        val call = comePetHomeAPI.getAllPetInfo(pageNumber, "${MainActivity.refreshToken}")

        call.enqueue(object : Callback<List<PetInfo>> {
            override fun onResponse(call: Call<List<PetInfo>>, response: Response<List<PetInfo>>) {
                if (response.isSuccessful) {
                    _currentUserPagePetInfoLiveData.value = response.body()
                } else {
                    logInRepository.loginUser(
                        MainActivity.loginId,
                        MainActivity.loginPassword,
                        { accessToken, refreshToken ->

                            MainActivity.accessToken = accessToken
                            MainActivity.refreshToken = refreshToken

                            MainActivity.accessToken?.let {  newAccessToken ->
                                getAllPetInfo(pageNumber, newAccessToken)
                            }

                        },
                        { errorMessage ->
                            Log.d("HomeFragment", "errorMessage: ${errorMessage}")
                        }
                    )
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

    fun likeAnimals(accessToken: String, petId: Int) {
        val call = comePetHomeAPI.likeAnimals(accessToken, petId)

        call.enqueue(object : Callback<PetInfoResponse> {
            override fun onResponse(call: Call<PetInfoResponse>, response: Response<PetInfoResponse>) {
                if (response.isSuccessful) {
                    Log.d("PetInfoFragment", "likeAnimals 의 response.body() : ${response.body()}")
                } else {
                    val errorBody = response.errorBody()?.string()

                    if (errorBody?.contains("\"code\":172") == true) {
                        refreshLikeAnimals(petId)
                    }
                }
            }

            override fun onFailure(call: Call<PetInfoResponse>, t: Throwable) {
                Log.d("PetInfoFragment", "네트워크 오류: ${t.message}")
            }

        })
    }

    fun refreshLikeAnimals(petId: Int) {
        val call = comePetHomeAPI.likeAnimals("${MainActivity.refreshToken}", petId)

        call.enqueue(object : Callback<PetInfoResponse> {
            override fun onResponse(call: Call<PetInfoResponse>, response: Response<PetInfoResponse>) {
                if (response.isSuccessful) {
                    Log.d("PetInfoFragment", "refreshLikeAnimals 의 response.body() : ${response.body()}")
                } else {
                    logInRepository.loginUser(
                        MainActivity.loginId,
                        MainActivity.loginPassword,
                        { accessToken, refreshToken ->

                            MainActivity.accessToken = accessToken
                            MainActivity.refreshToken = refreshToken

                            MainActivity.accessToken?.let {  newAccessToken ->
                                likeAnimals(newAccessToken, petId)
                            }

                        },
                        { errorMessage ->
                            Log.d("HomeFragment", "errorMessage: ${errorMessage}")
                        }
                    )
                }
            }

            override fun onFailure(call: Call<PetInfoResponse>, t: Throwable) {
                Log.d("PetInfoFragment", "네트워크 오류: ${t.message}")
            }

        })
    }

    fun unLikeAnimals(accessToken: String, petId: Int) {
        val call = comePetHomeAPI.unLikeAnimals(accessToken, petId)

        call.enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if (response.isSuccessful) {
                    Log.d("PetInfoFragment", "unLikeAnimals 의 response.body() : ${response.body()}")
                } else {
                    val errorBody = response.errorBody()?.string()

                    if (errorBody?.contains("\"code\":172") == true) {
                        refreshUnLikeAnimals(petId)
                    }
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                Log.d("PetInfoFragment", "네트워크 오류: ${t.message}")
            }

        })
    }

    fun refreshUnLikeAnimals(petId: Int) {
        val call = comePetHomeAPI.unLikeAnimals("${MainActivity.refreshToken}", petId)

        call.enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if (response.isSuccessful) {
                    Log.d("PetInfoFragment", "refreshUnLikeAnimals 의 response.body() : ${response.body()}")
                } else {
                    logInRepository.loginUser(
                        MainActivity.loginId,
                        MainActivity.loginPassword,
                        { accessToken, refreshToken ->

                            MainActivity.accessToken = accessToken
                            MainActivity.refreshToken = refreshToken

                            MainActivity.accessToken?.let {  newAccessToken ->
                                unLikeAnimals(newAccessToken, petId)
                            }

                        },
                        { errorMessage ->
                            Log.d("HomeFragment", "errorMessage: ${errorMessage}")
                        }
                    )
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                Log.d("PetInfoFragment", "네트워크 오류: ${t.message}")
            }

        })
    }

}