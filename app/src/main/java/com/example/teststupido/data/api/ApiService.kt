package com.example.teststupido.data.api

import com.example.teststupido.model.Pianeti.Pianeta
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("planets/{dato}")
    suspend fun getPianeti(@Path ("dato")pianeta:String):Pianeta

}