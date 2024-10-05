package com.meli.melichallenge.domain.usecase

import com.meli.melichallenge.data.model.Product
import com.meli.melichallenge.data.repository.ProductRepository

/**
 * Use case for searching products based on a query string.
 *
 * @property productRepository The repository responsible for handling product search operations.
 */
class SearchProductsUseCase(private val productRepository: ProductRepository) {

  /**
   * Executes the use case to search for products using the provided query string.
   *
   * @param query The search term used to find relevant products.
   * @return A list of products that match the search query.
   * @throws Exception if any error occurs during the search operation.
   */
  suspend operator fun invoke(query: String): List<Product> {
    return productRepository.searchProducts(query)
  }
}