package com.example.food.di

import com.example.food.data.repository.DefaultFoodRepository
import com.example.food.domain.repository.FoodRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@InstallIn(ActivityRetainedComponent::class)
@Module
abstract class FoodRepositoryModule {

    @ActivityRetainedScoped
    @Binds
    abstract fun bindFoodRepository(defaultFoodRepository: DefaultFoodRepository): FoodRepository
}