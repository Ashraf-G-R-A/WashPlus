package com.example.washplus.wash.data.mapper

import com.example.washplus.wash.data.model.ProductDto


fun ProductDto.toDomain(): ProductDto {
    return ProductDto(
        id = id,
        title = title,
        description = description,
        price = price,
        count = count,
        date = date
    )
}