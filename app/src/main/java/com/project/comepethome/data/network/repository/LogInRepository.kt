package com.project.comepethome.data.network.repository

import android.util.Log
import com.project.comepethome.data.model.LoginRequest
import com.project.comepethome.data.model.LoginResponse
import com.project.comepethome.data.network.api.GatewayServiceApi
import com.project.comepethome.ui.main.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LogInRepository {

    private val gatewayServiceApi: GatewayServiceApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://54.180.142.14:9001/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        gatewayServiceApi = retrofit.create(GatewayServiceApi::class.java)
    }

    fun loginUser(loginId: String,
                  loginPassword: String,
                  onSuccess: (accessToken: String?, refreshToken: String?) -> Unit,
                  onError: (String) -> Unit
    ) {
        val loginRequest = LoginRequest(loginId, loginPassword)

        val call = gatewayServiceApi.loginUser(loginRequest)
        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {

                if (response.isSuccessful) {
                    // Access-Token 및 Refresh-Token 값을 헤더에서 추출
                    MainActivity.accessToken = response.headers()["access-token"]
                    MainActivity.refreshToken = response.headers()["refresh-token"]

                    onSuccess.invoke(MainActivity.accessToken, MainActivity.refreshToken)
                } else {
                    onError.invoke("Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                onError.invoke("네트워크 오류: ${t.message}")
            }

        })

    }

}