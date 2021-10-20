package com.example.food.data.source.remote

import com.example.food.data.source.remote.response.FoodCategoriesResponse
import com.example.food.data.source.remote.response.MealsResponse

interface FoodApi {

    suspend fun getFoodCategories(): FoodCategoriesResponse

    suspend fun getMealsByCategory(categoryId: String): MealsResponse
}