package com.example.washplus.wash.domain.usecase

import com.example.washplus.wash.data.model.ProductDto
import com.example.washplus.wash.domain.repo.IWashPlus
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WashPlusUseCase @Inject constructor(val washPlusRepository: IWashPlus) {
    suspend fun addProduct(productDto: ProductDto) {
        washPlusRepository.addProduct(productDto)
    }

    suspend fun deleteProduct(productDto: ProductDto) {
        washPlusRepository.deleteProduct(productDto)
    }

    suspend fun getAllProducts(): Flow<List<ProductDto>> {
        return washPlusRepository.getAllProducts()
    }


    suspend fun payProduct(id: Int) {
        washPlusRepository.payProduct(id)
    }

    suspend fun updateProduct(productDto: ProductDto) {
        washPlusRepository.updateProduct(productDto)
    }

    suspend fun getProductById(id: Int): ProductDto {
        return washPlusRepository.getProductById(id)
    }


}