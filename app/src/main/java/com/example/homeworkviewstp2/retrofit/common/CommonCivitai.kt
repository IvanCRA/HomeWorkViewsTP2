package com.example.homeworkviewstp2.retrofit.common

import com.example.homeworkviewstp2.retrofit.RetrofitClientCivitai
import com.example.homeworkviewstp2.retrofit.RetrofitServiecesCivitai

object CommonCivitai {
    private val BASE_URL = "https://civitai.com/api/v1/"
    val retrofitServiecesCivitai: RetrofitServiecesCivitai
        get() = RetrofitClientCivitai.getClient(BASE_URL).create(RetrofitServiecesCivitai::class.java)
}