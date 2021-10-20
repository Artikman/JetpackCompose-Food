package com.example.food.di

import android.content.Context
import androidx.room.Room
import com.example.food.data.source.local.FoodDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class FoodDatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        Room.databaseBuilder(
            appContext,
            FoodDatabase::class.java,
            "food_database"
        )
            .allowMainThreadQueries()
            .build()

    @Singleton
    @Provides
    fun provideTodosDao(database: FoodDatabase) = database.getFoodDao()
}