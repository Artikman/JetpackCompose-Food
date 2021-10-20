package com.example.food.presentation.categories

import androidx.lifecycle.viewModelScope
import com.example.food.domain.usecase.GetFoodCategoriesUseCase
import com.example.food.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodCategoriesViewModel @Inject constructor(
    private val getFoodCategoriesUseCase: GetFoodCategoriesUseCase
) : BaseViewModel<FoodCategoriesViewState.Event, FoodCategoriesViewState.State, FoodCategoriesViewState.Effect>() {

    init {
        viewModelScope.launch { getFoodCategories() }
    }

    override fun setInitialState() =
        FoodCategoriesViewState.State(categories = listOf(), isLoading = true)

    override fun handleEvents(event: FoodCategoriesViewState.Event) {
        when (event) {
            is FoodCategoriesViewState.Event.CategorySelection -> {
                setEffect { FoodCategoriesViewState.Effect.Navigation.ToCategoryDetails(event.categoryName) }
            }
        }
    }

    private suspend fun getFoodCategories() {
        val categories = getFoodCategoriesUseCase()
        setState {
            copy(categories = categories, isLoading = false)
        }
    }
}
