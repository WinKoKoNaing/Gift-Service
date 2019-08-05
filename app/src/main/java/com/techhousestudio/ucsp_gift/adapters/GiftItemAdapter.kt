package com.techhousestudio.ucsp_gift.adapters

import android.view.ViewGroup
import android.widget.ProgressBar
import com.firebase.ui.firestore.paging.FirestorePagingAdapter
import com.firebase.ui.firestore.paging.FirestorePagingOptions
import com.techhousestudio.ucsp_gift.listeners.SetOnOrderButtonClickListener
import com.techhousestudio.ucsp_gift.models.GiftItem


class GiftItemAdapter(options: FirestorePagingOptions<GiftItem>) :
    FirestorePagingAdapter<GiftItem, GiftItemViewHolder>(options) {
    lateinit var setOnOrderButtonClickListener: SetOnOrderButtonClickListener


    lateinit var progressBar: ProgressBar
    override fun onBindViewHolder(hoder: GiftItemViewHolder, position: Int, giftItem: GiftItem) {
        hoder.bindTo(giftItem)

        hoder.btnOrder.setOnClickListener {
//            setOnOrderButtonClickListener.onOrderButtonClick(position)
        }

    }




//    override fun onLoadingStateChanged(state: LoadingState) {
//        when (state) {
//            LoadingState.LOADING_MORE ->{
//                Log.d("WKKN",itemCount.toString()+" LOAD More")
//                progressBar.visibility = View.VISIBLE
//            }
//                // Do your loading animation
//
//
//
//            LoadingState.LOADED -> {
//                progressBar.visibility = View.GONE
//                Log.d("WKKN",itemCount.toString()+" Loaded")
//                // Stop Animation
//            }
//
//            LoadingState.FINISHED ->
//            {
//                progressBar.visibility = View.GONE
//                Log.d("WKKN",itemCount.toString()+" finished")
//            }
//            //Reached end of Data set
//
//            LoadingState.ERROR -> retry()
//        }
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GiftItemViewHolder = GiftItemViewHolder(parent)


}