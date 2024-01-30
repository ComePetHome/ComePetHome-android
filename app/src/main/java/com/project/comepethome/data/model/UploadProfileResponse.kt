package com.project.comepethome.data.model

data class UploadProfileResponse(
    val userId: String,
    val imageUrls: List<String>,
    val multipartFile: Any?
)
