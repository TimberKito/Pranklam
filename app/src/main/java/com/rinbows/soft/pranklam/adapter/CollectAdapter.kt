package com.rinbows.soft.pranklam.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.rinbows.soft.pranklam.R
import com.rinbows.soft.pranklam.activity.PlayerActivity
import com.rinbows.soft.pranklam.data.DataListDTO
import com.rinbows.soft.pranklam.tools.AppConstant

class CollectAdapter(
    private val context: Context
) : RecyclerView.Adapter<CollectAdapter.ItemViewHolder>() {

    private var collectDataList: List<DataListDTO> = emptyList()

    fun updateData(dataList: List<DataListDTO>) {
        collectDataList = dataList
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val imgItemPer = itemView.findViewById<ImageView>(R.id.img_like)
        val textItemName = itemView.findViewById<TextView>(R.id.tv_like_name)
        val rootCard = itemView.findViewById<LinearLayout>(R.id.like_layout_rating)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.collect_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return collectDataList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        collectDataList[position].run {
            holder.textItemName.text = title
            Glide.with(context).load(preUrl).transition(DrawableTransitionOptions.withCrossFade())
                .error(R.drawable.img_error)
                .into(holder.imgItemPer)
            holder.rootCard.setOnClickListener() {
                context.startActivity(Intent(context, PlayerActivity::class.java).apply {
                    putExtra(AppConstant.KEY_EXTRA, this@run)
                })
            }
        }
    }

}