package com.example.washplus.wash.data.mapper

import com.example.washplus.wash.data.model.ProductDto
import com.example.washplus.wash.domain.model.MapperProductDto

fun MapperProductDto.toDto(): ProductDto {
    return ProductDto(
        id = id,
        title = title,
        description = description,
        price = price,
        count = count,
        date = date
    )
}