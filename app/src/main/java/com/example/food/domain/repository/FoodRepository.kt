package com.example.food.domain.repository

import com.example.food.domain.entity.FoodItem

interface FoodRepository {

    suspend fun getFoodCategories(): List<FoodItem>

    suspend fun getMealsByCategory(categoryId: String): List<FoodItem>
}