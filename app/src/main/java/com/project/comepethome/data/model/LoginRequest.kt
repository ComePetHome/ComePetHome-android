package com.project.comepethome.data.model

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("userId") val loginId : String,
    @SerializedName("password")val loginPassword: String
)

