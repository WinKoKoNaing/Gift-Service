package com.techhousestudio.ucsp_gift.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.firestore.FirebaseFirestore
import com.techhousestudio.ucsp_gift.R
import com.techhousestudio.ucsp_gift.adapters.ViewPagerAdapter
import com.techhousestudio.ucsp_gift.databinding.FragmentGiftDetailBinding
import com.techhousestudio.ucsp_gift.models.GiftItem
import kotlinx.android.synthetic.main.content_item_detail_info.*


class GiftDetailFragment : BottomSheetDialogFragment() {
    private var db = FirebaseFirestore.getInstance()
    var giftItem: GiftItem? = null
    private val args: GiftDetailFragmentArgs by navArgs()
    lateinit var binding: FragmentGiftDetailBinding

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window!!.setLayout(width, height)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_gift_detail, container, false)

        getGiftItemData(args.itemId)




        return binding.root
    }

    private fun getGiftItemData(id: String) {
        val docRef = db.collection("gift-items").document(id)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    giftItem = document.toObject(GiftItem::class.java)
                    binding.giftItem = giftItem
                    val viewPagerAdapter = ViewPagerAdapter(
                        context,
                        giftItem?.giftImage!!
                    )
                    binding.apply {
                        tab.setupWithViewPager(imageSlideViewPager, true)
                        imageSlideViewPager.adapter = viewPagerAdapter
                    }
                    Log.d("WKKN", "DocumentSnapshot data: ${document.data}")
                } else {
                    Log.d("WKKN", "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("WKKN", "get failed with ", exception)
            }
    }

}
