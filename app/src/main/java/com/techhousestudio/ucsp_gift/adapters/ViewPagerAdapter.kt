package com.techhousestudio.ucsp_gift.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.techhousestudio.ucsp_gift.R

class ViewPagerAdapter(val context: Context?, private val list: List<String>) : PagerAdapter() {
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context).inflate(R.layout.content_image_slide_row, container,false)

        val shopImage = view.findViewById<ImageView>(R.id.ivShopImage)
        val tvShopTitle = view.findViewById<TextView>(R.id.tvShopTitle)
        tvShopTitle.text = "Shopping"
        if (context != null) {
            Log.d("WKKN", list[position])
            Glide.with(context).load(list[position]).into(shopImage)
        }
        container.addView(view)
        return view

    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

}