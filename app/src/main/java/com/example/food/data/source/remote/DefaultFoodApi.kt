package com.example.food.data.source.remote

import com.example.food.data.source.remote.response.FoodCategoriesResponse
import com.example.food.data.source.remote.response.MealsResponse
import io.ktor.client.*
import io.ktor.client.request.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultFoodApi @Inject constructor(
    private val httpClient: HttpClient
) : FoodApi {

    override suspend fun getFoodCategories(): FoodCategoriesResponse {
        val categories: FoodCategoriesResponse = httpClient.get(API_URL_CATEGORIES)
        httpClient.close()
        return categories
    }

    override suspend fun getMealsByCategory(categoryId: String): MealsResponse {
        val meals: MealsResponse = httpClient.get(API_URL_MEALS) {
            parameter("c", "")
        }
        httpClient.close()
        return meals
    }

    companion object {
        const val API_URL_CATEGORIES = "https://www.themealdb.com/api/json/v1/1/categories.php"
        const val API_URL_MEALS = "https://www.themealdb.com/api/json/v1/1/filter.php"
    }
}
