package com.ilisi.unsplashtopimages.service

import retrofit2.http.GET

interface ImageApiService {
    @GET("/photos/random?count=30&client_id=d1E5Y9RLxdQ33qSIwJTfiShgZyXhexRmVR2UO2ljvo4")
    suspend fun getImages(): List<String>
}