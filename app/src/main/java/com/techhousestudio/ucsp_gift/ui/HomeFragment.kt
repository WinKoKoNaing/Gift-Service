package com.techhousestudio.ucsp_gift.ui


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.paging.PagedList
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.firebase.ui.firestore.paging.FirestorePagingOptions
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.Query
import com.techhousestudio.ucsp_gift.adapters.GiftItemRecyclerAdapter
import com.techhousestudio.ucsp_gift.adapters.ViewPagerAdapter
import com.techhousestudio.ucsp_gift.databinding.FragmentHomeBinding
import com.techhousestudio.ucsp_gift.models.GiftItem
import java.util.*


class HomeFragment : Fragment() {

    private lateinit var options: FirestorePagingOptions<GiftItem>
    lateinit var binding: FragmentHomeBinding
    lateinit var adapter: GiftItemRecyclerAdapter
    private val db = FirebaseFirestore.getInstance()
    private lateinit var giftItems: ArrayList<GiftItem?>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("WKKN", "On Create")
        val settings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(true)
            .build()
        db.firestoreSettings = settings

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)


        // setup layout manager
        binding.giftItemList.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        // setup page adapter
        val query = db
            .collection("gift-items").orderBy("name", Query.Direction.DESCENDING).limit(10)


        Log.d("WKKN", "onCreateView")
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setPrefetchDistance(1)
            .setPageSize(2)
            .build()


        options = FirestorePagingOptions.Builder<GiftItem>()
            .setLifecycleOwner(this@HomeFragment)
            .setQuery(query, config, GiftItem::class.java)
            .build()
        // adapter

//        adapter = GiftItemAdapter(options)

//        adapter.setOnOrderButtonClickListener = this

        query.get().addOnCompleteListener {
            giftItems = arrayListOf()

            if (it.isSuccessful) {
                for (document: DocumentSnapshot in it.result!!.iterator()) {
                    val giftItem = document.toObject(GiftItem::class.java)
                    giftItems.add(giftItem)
                }

                adapter = GiftItemRecyclerAdapter(giftItems)
                binding.giftItemList.adapter = adapter

            }
        }

//        binding.giftItemList.adapter = adapter

        // setup view pager
        val viewPagerAdapter = ViewPagerAdapter(
            context,
            arrayListOf(
                "http://www.mustasarepublic.com/wp-content/uploads/2018/11/Create-an-Effective-Video-Advertisement-With-These-9-Simple-Tips-1170x570.jpg",
                "https://www.codesgesture.com/auth/assets/upload/blog/Video-Advertisement-on-mobile-is-the-best-way-to-reach-your-Audiences.jpg"
            )
        )
        binding.apply {
            tab.setupWithViewPager(imageSlideViewPager, true)
            imageSlideViewPager.adapter = viewPagerAdapter
        }


        // listener

        binding.btnSave.setOnClickListener {
            insertGiftData()
        }


        return binding.root
    }

    override fun onStart() {
        super.onStart()

//        adapter.startListening()
        Log.d("WKKN", "OnStart")
//        Log.d("WKKN", ": "+adapter.itemCount)
//        if (adapter.itemCount <= 1) {
//
//        }else{
//            Log.d("WKKN", ""+adapter.itemCount)
//        }


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
        Log.d("WKKN", "Destory")
    }

}

