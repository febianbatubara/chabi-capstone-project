package com.chabi.android.chabiapp.data.source.local.entity

data class PersonalityEntity(
    val id: Int,
    val type: String,
    val percentage: Float? = null,
    val characteristic: String? = "",
    val uniqueness: String? = "",
    val description: String? = "",
    val videoUrl: String?= "",
    val characterName: String? = "",
    val characterImageUrl: String? = ""
)
