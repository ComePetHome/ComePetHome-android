package com.project.comepethome.data.model

import com.google.gson.annotations.SerializedName

data class JoinRequest(
    @SerializedName("userId") val joinId: String,
    @SerializedName("password") val joinPassword: String,
    @SerializedName("nickName") val joinNickname: String,
    @SerializedName("name") val joinName: String,
    @SerializedName("phoneNumber") val joinPhoneNumber: String,
)
