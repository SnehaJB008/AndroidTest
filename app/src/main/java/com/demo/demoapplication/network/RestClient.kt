package com.demo.demoapplication.network

import com.demo.demoapplication.module.Photo
import retrofit2.Call
import retrofit2.http.*

interface RestClient {
    @GET("photos/")
    fun getAlbum(): Call<ArrayList<Photo>?>?

    @GET("photos/{id}/")
    fun getPhotoById(@Path("id")photoId: Int): Call<Photo?>?
}