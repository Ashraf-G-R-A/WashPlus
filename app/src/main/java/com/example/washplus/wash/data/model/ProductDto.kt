package com.example.washplus.wash.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


data class ProductDto(

    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val count: Int,
    val date: String,
)