package com.project.comepethome.data.network.repository

import android.util.Log
import com.google.gson.GsonBuilder
import com.project.comepethome.data.model.EmailRequest
import com.project.comepethome.data.model.JoinRequest
import com.project.comepethome.data.model.JoinResponse
import com.project.comepethome.data.model.LoginResponse
import com.project.comepethome.data.network.api.JoinApi
import com.project.comepethome.ui.main.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JoinRepository {

    private val joinApi: JoinApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://54.180.142.14:9001/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
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

    fun checkUserId(
        userId: String,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        val call = joinApi.checkUserId(userId)
        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    val result = response.body()
                    onSuccess.invoke(result ?: "UserId is available") // 결과가 null이면 기본 메시지 출력
                } else {
                    onError.invoke("Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                onError.invoke("네트워크 오류: ${t.message}")
            }
        })
    }

    fun sendEmail(userId: String) {
        val call = joinApi.sendEmail(userId)
        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {

                } else {
                    val errorBody = response.errorBody()?.string()

                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {

            }

        })
    }

    fun certificationEmail(
        userId: String,
        code: String,
        onSuccess: (String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val emailRequest = EmailRequest(userId, code)

        val call = joinApi.certificationEmail(emailRequest)
        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    onSuccess.invoke("이메일 인증에 성공했습니다")
                } else {
                    onFailure.invoke("이메일 인증에 실패했습니다\n        다시 시도 해주세요")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                onFailure.invoke("이메일 인증에 실패했습니다\n        다시 시도 해주세요")
            }

        })

    }

    fun verificationEmail(
        userId: String,
        code: String,
        onSuccess: (String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val emailRequest = EmailRequest(userId, code)

        val call = joinApi.verificationEmail(emailRequest)
        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    MainActivity.accessToken = response.headers()["access-token"]
                    onSuccess.invoke("이메일 인증에 성공했습니다")
                } else {
                    onFailure.invoke("이메일 인증에 실패했습니다\n        다시 시도 해주세요")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                onFailure.invoke("이메일 인증에 실패했습니다\n        다시 시도 해주세요")
            }
        })
    }

}