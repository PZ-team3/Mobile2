package com.example.foodiemeetup.models

data class MapPointsResponseModel(
    val lat: Double,
    val lon: Double,
    val name: String,
    val address: String
) {
    constructor() : this(0.0,0.0,"","")
}
