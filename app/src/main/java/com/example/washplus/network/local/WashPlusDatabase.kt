package com.example.washplus.network.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.washplus.wash.data.model.ProductDto
import com.example.washplus.wash.domain.model.MapperProductDto

@Database(
    entities = [ProductDto::class],
    version = 2,
    exportSchema = false
)
abstract class WashPlusDatabase : RoomDatabase() {
    abstract val newsDao: WashPlusDao
}
