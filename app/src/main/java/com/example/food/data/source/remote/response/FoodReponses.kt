package com.example.food.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class FoodCategoriesResponse(
    val categories: List<FoodCategoryResponse>
)

data class MealsResponse(
    val meals: List<MealResponse>
)

data class FoodCategoryResponse(
    @SerializedName("idCategory")
    val id: String,
    @SerializedName("strCategory")
    val name: String,
    @SerializedName("strCategoryDescription")
    val description: String = ""
)

data class MealResponse(
    @SerializedName("idMeal")
    val id: String,
    @SerializedName("strMeal")
    val name: String,
)