package com.ilisi.myimagesapplication.networking

import com.ilisi.myimagesapplication.model.MyImageResponse
import retrofit2.Call
import retrofit2.http.*


interface ApiService {

    // Get current weather data
    @GET("/photos/random")
    fun getImages(
        @Query("client_id") key: String = ApiConfig.API_KEY,
        @Query("count") count: Int = 10
        ): Call<List<MyImageResponse>>
}