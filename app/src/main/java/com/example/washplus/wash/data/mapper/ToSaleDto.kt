package com.example.washplus.wash.data.mapper

import com.example.washplus.wash.data.model.SaleDto
import com.example.washplus.wash.domain.model.MapperProductDto


fun SaleDto.toDomain(): MapperProductDto {
    return MapperProductDto(
        id = id,
        title = title,
        description = description,
        price = price,
        count = count,
        date = ""
    )
}