package com.example.food.domain.usecase

import com.example.food.domain.base.BaseUseCase
import com.example.food.domain.entity.FoodItem
import com.example.food.domain.repository.FoodRepository
import javax.inject.Inject

class GetMealsByCategoryUseCase @Inject constructor(
    private val foodRepository: FoodRepository
) : BaseUseCase<String, List<FoodItem>> {

    override suspend fun invoke(params: String?): List<FoodItem> =
        foodRepository.getMealsByCategory(categoryId = params!!)
}