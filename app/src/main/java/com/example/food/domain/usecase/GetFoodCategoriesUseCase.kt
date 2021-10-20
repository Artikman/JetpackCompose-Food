package com.example.food.domain.usecase

import com.example.food.domain.base.BaseUseCase
import com.example.food.domain.entity.FoodItem
import com.example.food.domain.repository.FoodRepository
import javax.inject.Inject

class GetFoodCategoriesUseCase @Inject constructor(
    private val foodRepository: FoodRepository
) : BaseUseCase<Unit, List<FoodItem>> {

    override suspend fun invoke(params: Unit?): List<FoodItem> =
        foodRepository.getFoodCategories()
}