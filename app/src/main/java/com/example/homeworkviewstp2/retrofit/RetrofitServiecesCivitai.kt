package com.example.homeworkviewstp2.retrofit

import com.example.homeworkviewstp2.model.Civitai
import com.example.homeworkviewstp2.model.CivitaiResponse
import retrofit2.Call
import retrofit2.http.*
interface RetrofitServiecesCivitai {
    @GET("images?period=Day")
    fun getImagesList(@Query("limit") page: Int): Call<CivitaiResponse>
}