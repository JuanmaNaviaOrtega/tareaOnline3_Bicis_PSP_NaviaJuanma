package com.example.tareaonline3bicis

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface BiziApiService {
    @GET("sede/servicio/urbanismo-infraestructuras/estacion-bicicleta.json")
    suspend fun getStations(
        @Query("srsname") srsname: String = "wgs84",
        @Query("rows") rows: Int = 150,
        @Header("accept") accept: String = "application/json"
    ): Response<BiziResponse>

    companion object {
        const val BASE_URL = "https://www.zaragoza.es/"
    }
}