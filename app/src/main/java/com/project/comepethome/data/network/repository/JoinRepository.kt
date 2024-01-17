package com.project.comepethome.data.network.repository

import com.project.comepethome.data.model.JoinRequest
import com.project.comepethome.data.model.JoinResponse
import com.project.comepethome.data.network.api.JoinApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JoinRepository {

    private val joinApi: JoinApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://13.124.211.208:9001/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        joinApi = retrofit.create(JoinApi::class.java)
    }

    fun joinUser(
        joinId: String,
        joinPassword: String,
        joinNickname: String,
        joinName: String,
        joinPhoneNumber: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        val joinRequest = JoinRequest(joinId, joinPassword, joinNickname, joinName, joinPhoneNumber)

        val call = joinApi.joinUser(joinRequest)
        call.enqueue(object : Callback<JoinResponse> {
            override fun onResponse(call: Call<JoinResponse>, response: Response<JoinResponse>) {
                if (response.isSuccessful) {
                    onSuccess.invoke()
                } else {
                    onError.invoke("Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<JoinResponse>, t: Throwable) {
                onError.invoke("네트워크 오류: ${t.message}")
            }
        })
    }
}