package com.example.washplus.wash.data.repo

import com.example.washplus.network.local.WashPlusDao
import com.example.washplus.wash.data.model.ProductDto
import com.example.washplus.wash.domain.repo.IWashPlus
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WashPlusImpl @Inject constructor(private val washPlusDao: WashPlusDao) : IWashPlus {
    override suspend fun addProduct(productDto: ProductDto) {
        washPlusDao.addProduct(productDto)
    }

    override suspend fun deleteProduct(productDto: ProductDto) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllProducts(): Flow<List<ProductDto>> {
        return washPlusDao.getAllProducts()
    }

    override suspend fun payProduct(id: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun updateProduct(productDto: ProductDto) {
        TODO("Not yet implemented")
    }

    override suspend fun getProductById(id: Int): ProductDto {
        TODO("Not yet implemented")
    }

}