package com.meli.melichallenge.domain.usecase

import com.meli.melichallenge.data.model.ProductDetail
import com.meli.melichallenge.data.repository.ProductRepository

class GetProductByIdUseCase(private val productRepository: ProductRepository) {

  suspend operator fun invoke(id: String): ProductDetail {
    return productRepository.getProductById(id)
  }
}