package com.froztlass.mephisto.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.froztlass.mephisto.api.RetrofitInstance
import com.froztlass.mephisto.api.SuperheroResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//---  NAMA : FA'I REFRIANDI
//---  NIM : 10121079
//---  KELAS : PEM-ANDRO 3

class SuperheroViewModel : ViewModel() {

    private val _superhero = MutableStateFlow<SuperheroResponse?>(null)
    val superhero: StateFlow<SuperheroResponse?> get() = _superhero

    fun fetchSuperhero() {
        viewModelScope.launch {
            RetrofitInstance.api.getSuperhero().enqueue(object : Callback<SuperheroResponse> {
                override fun onResponse(call: Call<SuperheroResponse>, response: Response<SuperheroResponse>) {
                    if (response.isSuccessful) {
                        _superhero.value = response.body()
                    }
                }

                override fun onFailure(call: Call<SuperheroResponse>, t: Throwable) {
                    // Handle error
                }
            })
        }
    }
}
