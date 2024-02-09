package com.project.comepethome.data.model

data class PetInfo(
    val pet_id: Int,
    val name: String,
    val center: String,
    val enlistment_date: String,
    val breeds: String,
    val sex: String,
    val age: String,
    val adp_status: String,
    val like: Boolean,
    val thumbnail: String,
)
