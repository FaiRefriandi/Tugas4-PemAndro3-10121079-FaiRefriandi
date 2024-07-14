package com.froztlass.mephisto.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//---  NAMA : FA'I REFRIANDI
//---  NIM : 10121079
//---  KELAS : PEM-ANDRO 3

object RetrofitInstance {
    private const val BASE_URL = "https://www.superheroapi.com/api.php/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: SuperheroApiService by lazy {
        retrofit.create(SuperheroApiService::class.java)
    }
}
