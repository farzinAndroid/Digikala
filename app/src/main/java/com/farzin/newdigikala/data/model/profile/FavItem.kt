package com.farzin.newdigikala.data.model.profile

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.farzin.newdigikala.util.Constants.FAV_LIST_TABLE

@Entity(tableName = FAV_LIST_TABLE)
data class FavItem(
    @PrimaryKey
    val id:String,
    val name: String,
    val seller: String,
    val price: Long,
    val discountPercent: Int,
    val image: String,
    val star:Double,

)
