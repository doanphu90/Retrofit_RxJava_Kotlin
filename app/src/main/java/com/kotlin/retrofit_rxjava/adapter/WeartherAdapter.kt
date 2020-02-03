package com.kotlin.retrofit_rxjava.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.retrofit_rxjava.R
import com.kotlin.retrofit_rxjava.model.RequestWeather
import kotlinx.android.synthetic.main.item_wearther_adapter.view.*

class WeartherAdapter(private var listWearther: ArrayList<RequestWeather>, val cont:Context) :
    RecyclerView.Adapter<WeartherAdapter.WeartherHolder>() {

    fun updateData(item:RequestWeather){
        listWearther.add(item)
        notifyDataSetChanged()
    }

    class WeartherHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val timestamp = itemView.findViewById<TextView>(R.id.timestamp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeartherHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_wearther_adapter, parent, false)
        return WeartherHolder(view)
    }

    override fun getItemCount(): Int {
        return listWearther.size
    }

    override fun onBindViewHolder(holder: WeartherHolder, position: Int) {
        var itemWearther = listWearther[position]
        holder.timestamp.text = itemWearther.current?.temperature.toString()
    }
}