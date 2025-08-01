package com.example.washplus.wash.domain.repo

import com.example.washplus.wash.data.model.ProductDto
import kotlinx.coroutines.flow.Flow

interface IWashPlus {
    suspend fun addProduct(productDto: ProductDto)
    suspend fun deleteProduct(productDto: ProductDto)
    suspend fun getAllProducts(): Flow<List<ProductDto>>
    suspend fun payProduct(id: Int)
    suspend fun updateProduct(productDto: ProductDto)
    suspend fun getProductById(id: Int): ProductDto
}