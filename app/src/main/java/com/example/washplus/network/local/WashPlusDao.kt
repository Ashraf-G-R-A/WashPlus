package com.example.washplus.network.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.washplus.wash.data.model.ProductDto
import kotlinx.coroutines.flow.Flow

@Dao
interface WashPlusDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProduct(product: ProductDto)

    @Query("SELECT * FROM product_table")
    fun getAllProducts(): Flow<List<ProductDto>>
}
