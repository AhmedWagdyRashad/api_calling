package com.ahmedwagdy.apicalling.app.ui.weatherview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ahmedwagdy.apicalling.databinding.WeatherItemBinding
import com.ahmedwagdy.apicalling.domain.entity.WeatherData

class WeatherDataAdapter() :
    ListAdapter<WeatherData, WeatherDataAdapter.ViewHolder>(CategoryDiffCallback()) {
//private val listener: OnItemClickListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            WeatherItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
      /*  holder.itemView.setOnClickListener {
           listener.onItemClick(getItem(position))
        }*/
    }

    inner class ViewHolder(private val binding: WeatherItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
      /*  init {
           binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    if (item != null) {
                        listener.onItemClick(item)
                    }
                }
            }
        }*/

        fun bind(weatherData: WeatherData) {
            binding.apply {
                dateValueTv.text = weatherData.dt_txt
                weatherDescriptionTv.text = weatherData.description
                temperatureValueTv.text = weatherData.temp.toString()
                humidityValueTv.text = weatherData.humidity.toString()

            }
        }
    }

    class CategoryDiffCallback : DiffUtil.ItemCallback<WeatherData>() {
        override fun areItemsTheSame(
            oldItem: WeatherData,
            newItem: WeatherData
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: WeatherData,
            newItem: WeatherData
        ): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

 /*   interface OnItemClickListener {
        fun onItemClick(item: String)
    }*/
}