package com.techhousestudio.ucsp_gift.ui

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageSwitcher
import android.widget.ImageView
import android.widget.ViewSwitcher
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.firebase.firestore.FirebaseFirestore
import com.techhousestudio.ucsp_gift.R
import com.techhousestudio.ucsp_gift.databinding.ActivityGiftDetailBinding
import com.techhousestudio.ucsp_gift.models.GiftItem
import androidx.core.view.LayoutInflaterCompat.setFactory
import com.bumptech.glide.Glide


class GiftDetailActivity : AppCompatActivity() {
    var db = FirebaseFirestore.getInstance()
    var giftItem: GiftItem? = null
    lateinit var binding: ActivityGiftDetailBinding
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_gift_detail)


        // setup Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDefaultDisplayHomeAsUpEnabled(true)
        getGiftItemData(intent.getStringExtra("gift-id"))



    }

    private fun getGiftItemData(id: String) {
        val docRef = db.collection("gift-items").document(id)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    giftItem = document.toObject(GiftItem::class.java)
                    binding.giftItem = giftItem
                    Glide.with(this).load(giftItem!!.giftImage?.get(0)).into(binding.included.ivGiftImages)
                    Log.d("WKKN", "DocumentSnapshot data: ${document.data}")
                } else {
                    Log.d("WKKN", "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("WKKN", "get failed with ", exception)
            }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId==android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
