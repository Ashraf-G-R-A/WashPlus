package com.example.washplus.wash.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey


data class MapperProductDto(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val count: Int,
    val date: String
)
