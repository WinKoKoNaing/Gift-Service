package com.techhousestudio.ucsp_gift.ui


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.paging.PagedList
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.firebase.ui.firestore.paging.FirestorePagingOptions
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.techhousestudio.ucsp_gift.R
import com.techhousestudio.ucsp_gift.adapters.GiftItemAdapter
import com.techhousestudio.ucsp_gift.adapters.GiftItemRecyclerAdapter
import com.techhousestudio.ucsp_gift.adapters.ViewPagerAdapter
import com.techhousestudio.ucsp_gift.databinding.FragmentHomeBinding
import com.techhousestudio.ucsp_gift.models.GiftItem
import java.util.*
import kotlin.collections.ArrayList


class HomeFragment : Fragment() {

    private lateinit var options: FirestorePagingOptions<GiftItem>
    lateinit var binding: FragmentHomeBinding
    lateinit var adapter: GiftItemAdapter
    private val db = FirebaseFirestore.getInstance()
    private lateinit var giftItems: ArrayList<GiftItem?>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("WKKN","On Create")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)


        // setup layout manager
        binding.giftItemList.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

// setup page adapter
        val query = db
            .collection("gift-items").limit(5)


        Log.d("WKKN", "onCreateView")
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setPrefetchDistance(1)
            .setPageSize(2)
            .build()


        options = FirestorePagingOptions.Builder<GiftItem>()
            .setLifecycleOwner(this)
            .setQuery(query, config, GiftItem::class.java)
            .build()
        // adapter

        adapter = GiftItemAdapter(options)

//        adapter.setOnOrderButtonClickListener = this

//        query.get().addOnCompleteListener {
//            giftItems = arrayListOf()
//
//            if (it.isSuccessful) {
//                for (document: DocumentSnapshot in it.result!!.iterator()) {
//                    val giftItem = document.toObject(GiftItem::class.java)
//                    giftItems.add(giftItem)
//                }
//
//                adapter = GiftItemRecyclerAdapter(giftItems)
//                binding.giftItemList.adapter = adapter
//
//            }
//        }

        binding.giftItemList.adapter = adapter

        // setup view pager
        val viewPagerAdapter = ViewPagerAdapter(
            context,
            arrayListOf(
                "https://travel.jumia.com/blog/mm/wp-content/uploads/2017/03/re1-660x372.jpg",
                "https://i.ytimg.com/vi/-FDcwbxtBG8/maxresdefault.jpg"
            )
        )
        binding.tab.setupWithViewPager(binding.imageSlideViewPager,true)

        binding.imageSlideViewPager.adapter = viewPagerAdapter


        // listener

        binding.btnSave.setOnClickListener {
            insertGiftData()
        }


        return binding.root
    }

    override fun onStart() {
        super.onStart()
        Log.d("WKKN", "OnStart")
//        adapter.startListening()


    }


    private fun insertGiftData() {
        val giftItemRef = db.collection("gift-items").document()
        val giftItem = GiftItem(
            giftItemRef.id,
            "Apple Demo",
            "Fruits",
            "This is goood",
            200,
            5,
            mutableListOf("https://static.agcanada.com/wp-content/uploads/sites/5/2018/11/apple_GettyImages186843005_cmyk.jpg"),
            mutableListOf("small", "middle", "large"),
            true,
            Date()
        )

        giftItemRef.set(giftItem).addOnCompleteListener {
            Log.d("WKKN", "Success")
        }
            .addOnCanceledListener {
                Log.d("WKKN", "Fail")
            }
    }

    override fun onDestroy() {
        super.onDestroy()
//        adapter.stopListening()
    }

}

