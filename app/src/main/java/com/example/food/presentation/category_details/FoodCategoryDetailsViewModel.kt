package com.example.food.presentation.category_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.food.NavigationKeys
import com.example.food.data.repository.DefaultFoodRepository
import com.example.food.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodCategoryDetailsViewModel @Inject constructor(
    private val stateHandle: SavedStateHandle,
    private val repository: DefaultFoodRepository
) : BaseViewModel<FoodCategoryDetailsViewState.Event, FoodCategoryDetailsViewState.State, FoodCategoryDetailsViewState.Effect>() {

    init {
        viewModelScope.launch {
            val categoryId = stateHandle.get<String>(NavigationKeys.FOOD_CATEGORY_ID)
                ?: throw IllegalStateException()
            val categories = repository.getFoodCategories()
            val category = categories.first { it.id == categoryId }
            setState { copy(category = category) }

            val foodItems = repository.getMealsByCategory(categoryId)
            setState { copy(categoryFoodItems = foodItems) }
        }
    }

    override fun setInitialState() = FoodCategoryDetailsViewState.State(null, listOf())

    override fun handleEvents(event: FoodCategoryDetailsViewState.Event) {}
}