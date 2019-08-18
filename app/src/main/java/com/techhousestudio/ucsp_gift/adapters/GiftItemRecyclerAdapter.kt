package com.techhousestudio.ucsp_gift.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.techhousestudio.ucsp_gift.models.GiftItem

class GiftItemRecyclerAdapter(private val items: ArrayList<GiftItem?>) : RecyclerView.Adapter<GiftItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GiftItemViewHolder = GiftItemViewHolder(parent)

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: GiftItemViewHolder, position: Int) {
        holder.bindTo(items[position]!!)
    }

}
