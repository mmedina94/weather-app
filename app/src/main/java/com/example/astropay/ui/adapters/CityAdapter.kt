package com.example.astropay.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.astropay.R
import com.example.astropay.utils.ZERO_VALUE

class CityAdapter : RecyclerView.Adapter<CityViewHolder>() {

    private var data: ArrayList<String>? = null
    private var onClick: ((String) -> Unit)? = null

    fun setData(data: ArrayList<String>, onClick: (String) -> Unit) {
        this.data = data
        this.onClick = onClick
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        return CityViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.card_city, parent, false)
        )
    }

    override fun getItemCount() = data?.size ?: Int.ZERO_VALUE()

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        data?.let { data ->
            holder.bind(data[position])
            holder.itemView.setOnClickListener {
                onClick?.invoke(data[position])
            }
        }
    }
}