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

/**
 * Dagger module responsible for providing dependencies related to products and use cases.
 * It ensures that dependencies are available at the Singleton level.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

  /**
   * Provides a singleton instance of [ProductApiService].
   * Creates the service using Retrofit to handle API calls.
   *
   * @return An instance of [ProductApiService] for making network requests.
   */
  @Provides
  @Singleton
  fun provideProductApiService(): ProductApiService {
    return Retrofit.Builder()
      .baseUrl(ApiConstants.BASE_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
      .create(ProductApiService::class.java)
  }

  /**
   * Provides a singleton instance of [ProductRepository].
   * This repository interacts with [ProductApiService] to fetch product data.
   *
   * @param apiService The [ProductApiService] that the repository depends on.
   * @return An instance of [ProductRepository] to manage data operations.
   */
  @Provides
  @Singleton
  fun provideProductRepository(apiService: ProductApiService): ProductRepository {
    return ProductRepository(apiService)
  }

  /**
   * Provides a singleton instance of [SearchProductsUseCase].
   * This use case handles the product search functionality.
   *
   * @param productRepository The [ProductRepository] that this use case interacts with.
   * @return An instance of [SearchProductsUseCase] for searching products.
   */
  @Provides
  @Singleton
  fun provideSearchProductsUseCase(productRepository: ProductRepository): SearchProductsUseCase {
    return SearchProductsUseCase(productRepository)
  }

  /**
   * Provides a singleton instance of [GetProductByIdUseCase].
   * This use case fetches the details of a product by its ID.
   *
   * @param productRepository The [ProductRepository] that this use case interacts with.
   * @return An instance of [GetProductByIdUseCase] for fetching product details by ID.
   */
  @Provides
  @Singleton
  fun provideGetProductByIdUseCase(productRepository: ProductRepository): GetProductByIdUseCase {
    return GetProductByIdUseCase(productRepository)
  }
}