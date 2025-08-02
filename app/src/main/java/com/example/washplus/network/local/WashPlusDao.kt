package com.example.washplus.network.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.washplus.wash.data.model.ProductDto
import com.example.washplus.wash.data.model.SaleDto
import kotlinx.coroutines.flow.Flow

@Dao
interface WashPlusDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProduct(product: ProductDto)

    @Query("SELECT * FROM product_table")
    fun getAllProducts(): Flow<List<ProductDto>>

    @Query("DELETE FROM product_table WHERE id = :id")
    suspend fun deleteProduct(id: Int)

    @Query("SELECT * FROM product_table WHERE id = :id")
    suspend fun getProductById(id: Int): ProductDto

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateProduct(product: ProductDto)

    @Query("SELECT * FROM sales_table WHERE productId = :id")
    suspend fun getSaleById(id: Int): SaleDto

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSale(sale: SaleDto)

    @Query("UPDATE sales_table SET count = count + 1 WHERE id = :id")
    suspend fun incrementSaleCount(id: Int)

    @Query("UPDATE product_table SET count = count - 1 WHERE id = :id")
    suspend fun decrementProductCount(id: Int)

    @Query("DELETE FROM sales_table WHERE id = :id")
    suspend fun deleteSale(id: Int)

    @Query("SELECT * FROM sales_table")
    fun getAllSales(): Flow<List<SaleDto>>
}
