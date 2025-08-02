package com.example.washplus.network.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.washplus.wash.data.model.ProductDto
import com.example.washplus.wash.data.model.SaleDto
import com.example.washplus.wash.domain.model.MapperProductDto

@Database(
    entities = [ProductDto::class, SaleDto::class],
    version = 4,
    exportSchema = false
)
abstract class WashPlusDatabase : RoomDatabase() {
    abstract val newsDao: WashPlusDao
}
