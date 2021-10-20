package com.example.food.data.source.local.dao

import androidx.room.Dao
import androidx.room.Query

@Dao
interface FoodDao {

    @Query("SELECT * FROM food")
    fun getFoodCategories(): List<FoodEntity>

    @Query("SELECT * FROM food WHERE id = :id")
    fun getMealsByCategory(id: String): List<FoodEntity>
}