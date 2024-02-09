package com.project.comepethome.data.model

data class PetDetailsInfo(
    val pet_id: Int,
    val name: String,
    val center: String,
    val enlistment_date: String,
    val breeds: String,
    val sex: String,
    val age: String,
    val adp_status: String,
    val species: String,
    val weight: Double,
    val temporary_protection_status: String,
    val intro_url: String,
    val intro_contents: String,
    val temporary_protection_contents: String,
    val thumbnail_url: List<String>,
    val created_at: String,
    val updated_at: String,
)
