package com.example.food.data.source.local.dao

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.food.data.source.remote.response.FoodCategoriesResponse
import com.example.food.data.source.remote.response.MealsResponse
import com.example.food.domain.entity.FoodItem

@Entity(tableName = "food")
data class FoodEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String = ""
)

fun FoodCategoriesResponse.mapCategoriesToItems(): List<FoodItem> {
    return this.categories.map { category ->
        FoodItem(
            id = category.id,
            name = category.name,
            description = category.description
        )
    }
}

fun MealsResponse.mapMealsToItems(): List<FoodItem> {
    return this.meals.map { category ->
        FoodItem(
            id = category.id,
            name = category.name
        )
    }
}