package com.kumar.test.data.api

import com.kumar.test.data.model.Photo
import com.kumar.test.data.model.PhotoData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface RestApi {

    @GET("/photos")
    suspend fun getPictures(): Response<PhotoData>

    @GET

    suspend fun getPicture(
        @Path("id") id: Int
    ): Response<Photo>
}