package com.meli.melichallenge.data.repository

import com.meli.melichallenge.data.api.ProductApiService
import com.meli.melichallenge.data.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductRepository(private val apiService: ProductApiService) {

  suspend fun searchProducts(query: String): List<Product> {
    return withContext(Dispatchers.IO) {
      val response = apiService.searchProducts(query)
      if (response.isSuccessful) {
        response.body()?.results ?: emptyList()
      } else {
        throw Exception("Error fetching products: ${response.errorBody()?.string()}")
      }
    }
  }
}