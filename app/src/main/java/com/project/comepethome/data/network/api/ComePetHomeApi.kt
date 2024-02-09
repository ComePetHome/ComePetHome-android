package com.project.comepethome.data.network.api

import com.project.comepethome.data.model.PetDetailsInfo
import com.project.comepethome.data.model.PetInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ComePetHomeAPI {

    @GET("pet/pets")
    fun initialGetAllPetInfo(
        @Query("pageNumber") pageNumber: String
    ): Call<List<PetInfo>>


    @GET("pet/pets")
    fun getAllPetInfo(
        @Query("pageNumber") pageNumber: String,
        @Header("access-token") accessToken: String,
    ): Call<List<PetInfo>>

    @GET("pet/pets/{petId}")
    fun getPetDetailsInfo(
        @Path("petId") petId: Int
    ): Call<PetDetailsInfo>

}