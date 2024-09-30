package com.meli.melichallenge.di

import com.meli.melichallenge.data.api.ProductApiService
import com.meli.melichallenge.data.api.ApiModule
import com.meli.melichallenge.data.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

  @Provides
  @Singleton
  fun provideProductApiService(): ProductApiService {
    return ApiModule.productApiService
  }

  @Provides
  @Singleton
  fun provideProductRepository(apiService: ProductApiService): ProductRepository {
    return ProductRepository(apiService)
  }
}