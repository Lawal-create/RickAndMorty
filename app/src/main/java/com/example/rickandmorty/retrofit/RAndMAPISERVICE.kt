package com.example.rickandmorty.retrofit

import com.example.rickandmorty.models.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RAndMAPISERVICE {
        @GET("character")
        suspend fun getResponseForPage(@Query("page") page:Int): Response<ApiResponse>
}