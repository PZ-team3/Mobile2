package com.example.foodiemeetup.models

data class CreateMatchModel(
    val placeName: String,
    val date: String,
    val hour: Int,
    val minute: Int,
)
