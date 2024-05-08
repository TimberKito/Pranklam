package com.rinbows.soft.pranklam.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.rinbows.soft.pranklam.R
import com.rinbows.soft.pranklam.data.DataListDTO
import com.rinbows.soft.pranklam.listener.DetailsItemClickListener

class DataAdapter(
    private val context: Context,
    private val dataList: List<DataListDTO>,
    private val onDetailsItemClickListener: DetailsItemClickListener
) : RecyclerView.Adapter<DataAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgItemPer = itemView.findViewById<ImageView>(R.id.img_details_per)
        val textItemName = itemView.findViewById<TextView>(R.id.text_details_name)
        val rootCard = itemView.findViewById<CardView>(R.id.item_DetailsCard_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.data_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val dataListModel = dataList[position]
        holder.textItemName.text = dataListModel.title

        try {
            Glide.with(context).load(dataListModel.preUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(R.drawable.img_error)
                .into(holder.imgItemPer)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        holder.rootCard.setOnClickListener() {
            onDetailsItemClickListener.onItemClick(position, dataListModel)
        }
    }
}