package com.illia.finalproject.data.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.illia.finalproject.databinding.RecyclerRowBinding

class ForecastAdapter(private val onItemClick: (position: Int) -> Unit) :
RecyclerView.Adapter<ForecastAdapter.ForecastHolder>() {

    private val values = ArrayList<WeatherForecastDTO>()

     fun clearValues(){
        values.clear()
    }


     fun setList(list: List<WeatherForecastDTO>){
        values.clear()
        values.addAll(list)
        notifyDataSetChanged()
    }

    class ForecastHolder(
        private val itemBinding: RecyclerRowBinding,
        private val onItemClick: (position: Int) -> Unit
    ) :
        RecyclerView.ViewHolder(itemBinding.root) {
        init {
            itemView.setOnClickListener {
                onItemClick(adapterPosition)
            }
        }

        fun bind(content: String, url: String?) {

            Glide.with(itemView)
                .load(url)
                .centerCrop()
                .placeholder(itemBinding.imagePlaceholder.drawable)
                .into(itemBinding.imagePlaceholder)
            itemBinding.previewPlaceholder.setText(content)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastHolder {
        val itemBinding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context))
        return ForecastHolder(itemBinding, onItemClick)
    }

    override fun onBindViewHolder(holder: ForecastHolder, position: Int) {
        val forecast = values[position]

        holder.bind(forecast.timePeriod, forecast.image)
    }

    override fun getItemCount(): Int {
        return values.size
    }
}