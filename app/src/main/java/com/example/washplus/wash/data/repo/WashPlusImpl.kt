package com.example.washplus.wash.data.repo

import DateUtil
import com.example.washplus.network.local.WashPlusDao
import com.example.washplus.wash.data.model.MonthReport
import com.example.washplus.wash.data.model.ProductDto
import com.example.washplus.wash.data.model.SaleDto
import com.example.washplus.wash.data.model.YearReport
import com.example.washplus.wash.domain.repo.IWashPlus
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WashPlusImpl @Inject constructor(private val washPlusDao: WashPlusDao) : IWashPlus {
    override suspend fun addProduct(productDto: ProductDto) {
        washPlusDao.addProduct(productDto)
    }

    override suspend fun deleteProduct(id: Int) {
        washPlusDao.deleteProduct(id)
    }

    override suspend fun getAllProducts(): Flow<List<ProductDto>> {
        return washPlusDao.getAllProducts()
    }

    override suspend fun payProduct(productDto: ProductDto) {
        val updateCount = productDto.count - 1
        if (updateCount <= 0) {
            washPlusDao.deleteProduct(productDto.id)
        } else {
            washPlusDao.decrementProductCount(productDto.id)
        }

        val existingSale = washPlusDao.getSaleById(productDto.id)
        if (existingSale != null) {
            washPlusDao.incrementSaleCount(productDto.id)
        } else {
            val newSale = SaleDto(
                productId = productDto.id,
                title = productDto.title,
                description = productDto.description,
                price = productDto.price,
                count = 1,
                date = DateUtil.getCurrentDate(),
                id = 0
            )
            washPlusDao.insertSale(newSale)
        }
    }


    override suspend fun updateProduct(productDto: ProductDto) {
        TODO("Not yet implemented")
    }

    override suspend fun getProductById(id: Int): ProductDto {
        return washPlusDao.getProductById(id)
    }

    override suspend fun getSaleById(id: Int): SaleDto {
        return washPlusDao.getSaleById(id)
    }


    override suspend fun deleteSaleProduct(id: Int) {
        return washPlusDao.deleteSale(id)
    }

    override fun getAllSales(): Flow<List<SaleDto>> {
        return washPlusDao.getAllSales()
    }

    override fun generateReport(): String {
        TODO("Not yet implemented")
    }

    override suspend fun getAllSalesOnce(): List<YearReport> {
        val sales = washPlusDao.getAllSalesOnce()
        return sales
            .groupBy { sale ->
                sale.date.substring(0, 4).toInt()
            }
            .map { (year, yearSales) ->
                val months = yearSales
                    .groupBy { sale ->
                        sale.date.substring(5, 7).toInt()
                    }
                    .map { (month, monthSales) ->
                        MonthReport(
                            month,
                            monthSales.sortedBy { it.date })
                    }
                    .sortedBy { it.month }

                YearReport(year, months)
            }
            .sortedBy { it.year }
    }

}