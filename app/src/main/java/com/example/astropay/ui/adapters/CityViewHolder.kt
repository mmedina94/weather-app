package com.example.astropay.ui.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_city.view.*

class CityViewHolder : RecyclerView.ViewHolder {
    constructor(itemView: View) : super(itemView)

    fun bind(cityName: String) {
        itemView.textViewCityName.text = cityName
    }

}