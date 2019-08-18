package com.techhousestudio.ucsp_gift.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.techhousestudio.ucsp_gift.R
import com.techhousestudio.ucsp_gift.models.GiftItem
import com.techhousestudio.ucsp_gift.ui.GiftDetailFragment
import com.techhousestudio.ucsp_gift.ui.GiftDetailFragmentArgs
import com.techhousestudio.ucsp_gift.ui.HomeFragmentDirections

class GiftItemViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(

    LayoutInflater.from(parent.context).inflate(
        R.layout.recycler_home_gift_item_row,
        parent,
        false
    )
) {
    private val tvItemName: TextView = itemView.findViewById(R.id.tvItemName)
    private val ivItemImage: ImageView = itemView.findViewById(R.id.ivItemImage)
    private val tvPrice: TextView = itemView.findViewById(R.id.tvItemPrice)
    private val giftLayout: ConstraintLayout = itemView.findViewById(R.id.giftLayout)
    val btnOrder: MaterialButton = itemView.findViewById(R.id.btnOrder)


    fun bindTo(giftItem: GiftItem) {
        Glide.with(itemView.context).load(giftItem.giftImage?.get(0)).into(ivItemImage)
        tvPrice.text = "${giftItem.price} kyats"
        tvItemName.text = giftItem.name


        giftLayout.setOnClickListener {
            val direction = HomeFragmentDirections.actionHomeFragmentToGiftDetailFragment(giftItem.id!!)
            it.findNavController().navigate(direction)

        }
    }

}