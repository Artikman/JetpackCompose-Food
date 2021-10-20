package com.example.food.di

import com.example.food.data.source.remote.DefaultFoodApi
import com.example.food.data.source.remote.FoodApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.logging.*
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class FoodApiModule {

    @Singleton
    @Provides
    fun provideKtorHttpClient(): HttpClient {
        return HttpClient(io.ktor.client.engine.okhttp.OkHttp) {
            install(JsonFeature) {
                GsonSerializer()
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
        }
    }

    @Singleton
    @Provides
    fun provideTodosApi(
        okHttpClient: HttpClient
    ): FoodApi {
        return DefaultFoodApi(okHttpClient)
    }
}