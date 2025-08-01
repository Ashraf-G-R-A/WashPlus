package com.example.washplus.wash.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_table")
data class ProductDto(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val price: Double,
    val count: Int,
    val date: String,
)