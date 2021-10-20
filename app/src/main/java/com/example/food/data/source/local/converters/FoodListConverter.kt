package com.example.food.data.source.local.converters

import androidx.room.TypeConverter
import com.example.food.data.fromJson
import com.example.food.data.source.local.dao.FoodEntity
import com.google.gson.Gson

class FoodListConverter {
    private val gson = Gson()

    @TypeConverter
    fun toColumn(list: List<FoodEntity>): String = gson.toJson(list)

    @TypeConverter
    fun toList(column: String): List<FoodEntity> = gson.fromJson(column)!!
}