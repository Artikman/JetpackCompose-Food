package com.example.food.presentation.category_details

import com.example.food.domain.entity.FoodItem
import com.example.food.presentation.base.Action
import com.example.food.presentation.base.ViewEvent
import com.example.food.presentation.base.ViewState

class FoodCategoryDetailsViewState {

    data class State(
        val category: FoodItem?,
        val categoryFoodItems: List<FoodItem>
    ) : ViewState

    sealed class Effect : Action

    sealed class Event : ViewEvent
}