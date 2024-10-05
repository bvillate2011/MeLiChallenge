package com.meli.melichallenge.domain.usecase

import com.meli.melichallenge.data.model.ProductDetail
import com.meli.melichallenge.data.repository.ProductRepository

/**
 * Use case for retrieving the details of a product by its ID.
 *
 * @property productRepository The repository that handles data operations for products.
 */
class GetProductByIdUseCase(private val productRepository: ProductRepository) {

  /**
   * Executes the use case to fetch a product's details by its ID.
   *
   * @param id The unique ID of the product to retrieve.
   * @return The details of the product as [ProductDetail].
   * @throws Exception if the product is not found or if any error occurs during the operation.
   */
  suspend operator fun invoke(id: String): ProductDetail {
    return productRepository.getProductById(id)
  }
}