package com.example.washplus.wash.domain.model


data class MapperProductDto(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val count: Int,
    val date: String
)
