package com.example.tareaonline3bicis

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val station = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("STATION_DATA", BiziStation::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("STATION_DATA")
        }

        if (station == null) {
            Toast.makeText(this, "Error al cargar los datos de la estación", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        displayStationData(station)
    }

    private fun displayStationData(station: BiziStation) {
        findViewById<TextView>(R.id.tvTitle).text = station.title
        findViewById<TextView>(R.id.tvAddress).text = station.address ?: "Dirección no disponible"
        findViewById<TextView>(R.id.tvStatus).text = "Estado: ${if (station.isOperative()) "Operativa" else "No operativa"}"
        findViewById<TextView>(R.id.tvBikes).text = "Bicis disponibles: ${station.bicisDisponibles}"
        findViewById<TextView>(R.id.tvDocks).text = "Anclajes disponibles: ${station.anclajesDisponibles}"

        val formattedDate = try {
            val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
            val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
            formatter.format(parser.parse(station.lastUpdated) ?: station.lastUpdated)
        } catch (e: Exception) {
            station.lastUpdated
        }
        findViewById<TextView>(R.id.tvUpdated).text = "Última actualización: $formattedDate"

        findViewById<TextView>(R.id.tvCoordinates).text =
            "Coordenadas: (${"%.6f".format(station.geometry.coordinates[1])}, " +
                    "${"%.6f".format(station.geometry.coordinates[0])})"
    }
}