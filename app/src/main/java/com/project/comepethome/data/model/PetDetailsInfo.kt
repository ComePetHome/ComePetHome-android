package com.project.comepethome.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PetDetailsInfo(
    val pet_id: Int = 0,
    val name: String = "",
    val center: String = "",
    val enlistment_date: String = "",
    val breeds: String = "",
    val sex: String = "",
    val age: String = "",
    val adp_status: String = "",
    val species: String = "",
    val weight: Double = 0.0,
    val temporary_protection_status: String = "",
    val intro_url: String = "",
    val intro_contents: String = "",
    val temporary_protection_contents: String = "",
    val thumbnail_url: List<String> = listOf(),
    val created_at: String = "",
    val updated_at: String = ""
) : Parcelable
