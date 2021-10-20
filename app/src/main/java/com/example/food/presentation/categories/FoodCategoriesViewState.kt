package com.example.food.presentation.categories

import com.example.food.domain.entity.FoodItem
import com.example.food.presentation.base.Action
import com.example.food.presentation.base.ViewEvent
import com.example.food.presentation.base.ViewState

class FoodCategoriesViewState {

    data class State(
        val categories: List<FoodItem> = listOf(), val isLoading: Boolean = false
    ) : ViewState

    sealed class Effect : Action {
        sealed class Navigation : Effect() {
            data class ToCategoryDetails(val categoryName: String) : Navigation()
        }
    }

    sealed class Event : ViewEvent {
        data class CategorySelection(val categoryName: String) : Event()
    }
}