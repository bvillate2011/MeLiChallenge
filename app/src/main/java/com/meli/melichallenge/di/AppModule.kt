package com.meli.melichallenge.di

import com.meli.melichallenge.data.ApiConstants
import com.meli.melichallenge.data.api.ProductApiService
import com.meli.melichallenge.data.repository.ProductRepository
import com.meli.melichallenge.domain.usecase.GetProductByIdUseCase
import com.meli.melichallenge.domain.usecase.SearchProductsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

  @Provides
  @Singleton
  fun provideProductApiService(): ProductApiService {
    return Retrofit.Builder()
      .baseUrl(ApiConstants.BASE_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
      .create(ProductApiService::class.java)
  }

  @Provides
  @Singleton
  fun provideProductRepository(apiService: ProductApiService): ProductRepository {
    return ProductRepository(apiService)
  }

  @Provides
  @Singleton
  fun provideSearchProductsUseCase(productRepository: ProductRepository): SearchProductsUseCase {
    return SearchProductsUseCase(productRepository)
  }

  @Provides
  @Singleton
  fun provideGetProductByIdUseCase(productRepository: ProductRepository): GetProductByIdUseCase {
    return GetProductByIdUseCase(productRepository)
  }
}