package com.example.washplus.wash.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sales_table")
data class SaleDto(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val description: String,
    val productId: Int,
    val price: Double,
    var count: Int,
    val date: String
)
