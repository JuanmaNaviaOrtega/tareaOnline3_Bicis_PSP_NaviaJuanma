package com.example.tareaonline3bicis

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import android.util.Log

class BiziViewModel(application: Application) : AndroidViewModel(application) {
    private val _stations = MutableLiveData<List<BiziStation>>()
    val stations: LiveData<List<BiziStation>> = _stations

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    init {
        loadStations()
    }

    fun loadStations() {
        _isLoading.value = true
        _errorMessage.value = null

        viewModelScope.launch {
            try {
                Log.d("BiziViewModel", "Cargando estaciones...")
                val response = RetrofitClient.instance.getStations()

                if (response.isSuccessful) {
                    response.body()?.let { apiResponse ->
                        _stations.value = apiResponse.result
                        Log.d("BiziViewModel", "Estaciones cargadas: ${apiResponse.result.size}")
                    }
                } else {
                    _errorMessage.value = "Error del servidor: ${response.code()}"
                    Log.e("BiziViewModel", "Error en la respuesta: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error de conexión: ${e.localizedMessage}"
                Log.e("BiziViewModel", "Error al cargar estaciones", e)
            } finally {
                _isLoading.value = false
            }
        }
    }
}