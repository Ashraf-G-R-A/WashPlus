package com.example.washplus.wash.domain.repo

import com.example.washplus.wash.data.model.ProductDto
import com.example.washplus.wash.data.model.SaleDto
import com.example.washplus.wash.data.model.YearReport
import kotlinx.coroutines.flow.Flow

interface IWashPlus {
    suspend fun addProduct(productDto: ProductDto)
    suspend fun deleteProduct(id: Int)
    suspend fun getAllProducts(): Flow<List<ProductDto>>
    suspend fun payProduct(productDto: ProductDto)
    suspend fun updateProduct(productDto: ProductDto)
    suspend fun getProductById(id: Int): ProductDto
    suspend fun getSaleById(id: Int): SaleDto
    suspend fun deleteSaleProduct(id: Int)
    fun getAllSales(): Flow<List<SaleDto>>
    fun generateReport(): String

    suspend fun getAllSalesOnce(): List<YearReport>
}