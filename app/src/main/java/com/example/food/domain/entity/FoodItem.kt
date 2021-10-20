package com.example.food.domain.entity

data class FoodItem(
    val id: String,
    val name: String,
    val description: String = ""
)