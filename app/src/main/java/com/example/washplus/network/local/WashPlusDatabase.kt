package com.example.washplus.network.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.washplus.wash.domain.model.MapperProductDto

@Database(
    entities = [MapperProductDto::class],
    version = 1,
    exportSchema = false
)
abstract class WashPlusDatabase : RoomDatabase() {
    abstract val newsDao: WashPlusDao
}
