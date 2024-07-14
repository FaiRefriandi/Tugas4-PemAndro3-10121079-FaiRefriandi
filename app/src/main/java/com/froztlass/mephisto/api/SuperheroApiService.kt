package com.froztlass.mephisto.api

import retrofit2.Call
import retrofit2.http.GET

//---  NAMA : FA'I REFRIANDI
//---  NIM : 10121079
//---  KELAS : PEM-ANDRO 3

interface SuperheroApiService {
    @GET("08fb65f0a622043784488a60fca7c980/443")
    fun getSuperhero(): Call<SuperheroResponse>
}
