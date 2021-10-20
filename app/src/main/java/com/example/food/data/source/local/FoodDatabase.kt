package com.example.food.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.food.data.source.local.converters.FoodListConverter
import com.example.food.data.source.local.dao.FoodDao
import com.example.food.data.source.local.dao.FoodEntity

@Database(
    entities = [
        FoodEntity::class
    ],
    version = 2,
    exportSchema = false
)
@TypeConverters(
    value = [
        FoodListConverter::class
    ]
)
abstract class FoodDatabase : RoomDatabase() {
    abstract fun getFoodDao(): FoodDao
}