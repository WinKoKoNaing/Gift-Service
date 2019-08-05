package com.techhousestudio.ucsp_gift.models

import java.util.*

data class GiftItem(
    var id: String? = null,
    var name: String? = null,
    var category: String? = null,
    var description: String? = null,
    var price: Long? = 0,
    var quantity: Int? = 0,
    var giftImage: List<String>? = null,
    var size: List<String>? = null,
    var isAvailable: Boolean? = false,
    var date: Date? = null


){

}