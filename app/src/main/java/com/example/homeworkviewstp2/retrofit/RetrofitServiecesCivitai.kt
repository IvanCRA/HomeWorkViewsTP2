package com.example.homeworkviewstp2.retrofit

import com.example.homeworkviewstp2.model.Civitai
import retrofit2.Call
import retrofit2.http.*
interface RetrofitServiecesCivitai {
    @GET("images?limit=5")
    fun getImagesList(): Call<MutableList<Civitai>>
}