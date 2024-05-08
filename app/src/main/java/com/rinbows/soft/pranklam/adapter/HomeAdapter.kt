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
import com.rinbows.soft.pranklam.data.CategoryDTO
import com.rinbows.soft.pranklam.listener.HomeClickListener

class HomeAdapter(
    private val context: Context,
    private val categoryList: List<CategoryDTO>,
    private val onHomeItemClickListener: HomeClickListener
) : RecyclerView.Adapter<HomeAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgCategoryPer = itemView.findViewById<ImageView>(R.id.img_category_per)
        val textCategoryName = itemView.findViewById<TextView>(R.id.text_category_name)
        val rootCard = itemView.findViewById<CardView>(R.id.item_HomeCard_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.home_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val category = categoryList[position]
        holder.textCategoryName.text = category.categoryName

        try {
            Glide.with(context).load(category.categoryUrl).transition(DrawableTransitionOptions.withCrossFade())

                .error(R.drawable.img_error)

                .into(holder.imgCategoryPer)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        holder.rootCard.setOnClickListener() {
            onHomeItemClickListener.onItemClick(position, category)
        }
    }

}