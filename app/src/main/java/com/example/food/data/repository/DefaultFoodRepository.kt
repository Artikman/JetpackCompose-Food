package com.example.food.data.repository

import com.example.food.data.source.local.dao.FoodDao
import com.example.food.data.source.local.dao.mapCategoriesToItems
import com.example.food.data.source.local.dao.mapMealsToItems
import com.example.food.data.source.remote.FoodApi
import com.example.food.domain.entity.FoodItem
import com.example.food.domain.repository.FoodRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultFoodRepository @Inject constructor(
    private val foodApi: FoodApi,
    private val foodDao: FoodDao
) : FoodRepository {

    private var cachedCategory: List<FoodItem>? = null

    override suspend fun getMealsByCategory(categoryId: String): List<FoodItem> =
        withContext(Dispatchers.IO) {
            val categoryName = getFoodCategories().first { it.id == categoryId }.name
            val mealsByCategoryResponse = foodApi.getMealsByCategory(categoryName).mapMealsToItems()
            if (mealsByCategoryResponse.isNotEmpty()) {
                foodDao.getMealsByCategory(categoryId)
            }
            return@withContext mealsByCategoryResponse
        }


    override suspend fun getFoodCategories(): List<FoodItem> =
        withContext(Dispatchers.IO) {
            var cachedCategories = cachedCategory
            if (cachedCategories == null) {
                cachedCategories = foodApi.getFoodCategories().mapCategoriesToItems()
                cachedCategory = cachedCategories
            } else {
                foodDao.getFoodCategories()
            }
            return@withContext cachedCategories
        }
}