package com.meli.melichallenge.domain.usecase

import com.meli.melichallenge.data.model.Product
import com.meli.melichallenge.data.repository.ProductRepository

class SearchProductsUseCase(private val productRepository: ProductRepository) {

  suspend operator fun invoke(query: String): List<Product> {
    return productRepository.searchProducts(query)
  }
}