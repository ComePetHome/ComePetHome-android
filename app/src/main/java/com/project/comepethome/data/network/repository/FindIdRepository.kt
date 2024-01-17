package com.project.comepethome.data.network.repository

import com.project.comepethome.data.model.FindIdRequest
import com.project.comepethome.data.model.FindIdResponse
import com.project.comepethome.data.network.api.FindIdApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FindIdRepository {

    private val findIdApi: FindIdApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://13.124.211.208:9001/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        findIdApi = retrofit.create(FindIdApi::class.java)
    }

    fun findUserId(
        name: String,
        phoneNumber: String,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        val findIdRequest = FindIdRequest(name, phoneNumber)

        val call = findIdApi.findUserId(findIdRequest)
        call.enqueue(object : Callback<FindIdResponse> {
            override fun onResponse(call: Call<FindIdResponse>, response: Response<FindIdResponse>) {
                if (response.isSuccessful) {
                    val userId = response.body()?.userId ?: ""
                    onSuccess.invoke(userId)
                } else {
                    onError.invoke("Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<FindIdResponse>, t: Throwable) {
                onError.invoke("네트워크 오류: ${t.message}")
            }
        })

    }
}