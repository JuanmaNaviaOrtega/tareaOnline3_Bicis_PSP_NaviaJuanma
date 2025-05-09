package com.example.tareaonline3bicis

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class StationAdapter(
    private val onClick: (BiziStation) -> Unit
) : ListAdapter<BiziStation, StationAdapter.StationViewHolder>(StationDiffCallback) {

    inner class StationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvName: TextView = itemView.findViewById(R.id.tvStationName)
        private val tvBikes: TextView = itemView.findViewById(R.id.tvBikesAvailable)
        private val tvDocks: TextView = itemView.findViewById(R.id.tvDocksAvailable)
        private val tvStatus: TextView = itemView.findViewById(R.id.tvStatus)

        fun bind(station: BiziStation) {
            tvName.text = station.title
            tvBikes.text = "🚲 ${station.bicisDisponibles}"
            tvDocks.text = "🔒 ${station.anclajesDisponibles}"
            tvStatus.text = if (station.isOperative()) "✅" else "❌"

            itemView.setOnClickListener { onClick(station) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_station, parent, false)
        return StationViewHolder(view)
    }

    override fun onBindViewHolder(holder: StationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val StationDiffCallback = object : DiffUtil.ItemCallback<BiziStation>() {
            override fun areItemsTheSame(oldItem: BiziStation, newItem: BiziStation): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: BiziStation, newItem: BiziStation): Boolean {
                return oldItem == newItem
            }
        }
    }
}