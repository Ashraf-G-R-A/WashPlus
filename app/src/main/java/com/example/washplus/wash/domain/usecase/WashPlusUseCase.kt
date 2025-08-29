package com.example.washplus.wash.domain.usecase

import android.util.Log
import com.example.washplus.wash.data.model.ProductDto
import com.example.washplus.wash.data.model.SaleDto
import com.example.washplus.wash.data.model.YearReport
import com.example.washplus.wash.domain.repo.IWashPlus
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WashPlusUseCase @Inject constructor(private val washPlusRepository: IWashPlus) {
    suspend fun addProduct(productDto: ProductDto) {
        washPlusRepository.addProduct(productDto)
    }

    suspend fun deleteProduct(productId: Int) {
        washPlusRepository.deleteProduct(productId)
    }

    suspend fun getAllProducts(): Flow<List<ProductDto>> {
        return washPlusRepository.getAllProducts()
    }


    suspend fun payProduct(productDto: ProductDto) {
        washPlusRepository.payProduct(productDto)
    }

    suspend fun updateProduct(productDto: ProductDto) {
        washPlusRepository.updateProduct(productDto)
    }

    suspend fun getProductById(id: Int): ProductDto {
        return washPlusRepository.getProductById(id)
    }

    suspend fun getSaleById(id: Int): SaleDto {
        return washPlusRepository.getSaleById(id)
    }

    suspend fun deleteSaleProduct(id: Int) {
        washPlusRepository.deleteSaleProduct(id)
    }

    fun getAllSales(): Flow<List<SaleDto>> {
        return washPlusRepository.getAllSales()
    }

    suspend fun getAllSalesOnce(): List<YearReport> {
        Log.d("UseCase", "Getting all sales once ${washPlusRepository.getAllSalesOnce()}")
        return washPlusRepository.getAllSalesOnce()
    }

}