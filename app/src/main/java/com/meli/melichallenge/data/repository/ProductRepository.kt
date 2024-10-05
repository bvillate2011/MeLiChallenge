package com.meli.melichallenge.data.repository

import com.meli.melichallenge.data.api.ProductApiService
import com.meli.melichallenge.data.model.Product
import com.meli.melichallenge.data.model.ProductDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Repository responsible for interacting with the Product API.
 * Handles network requests for product searches and fetching product details.
 *
 * @property apiService The API service used to make network requests.
 */
open class ProductRepository(private val apiService: ProductApiService) {

  /**
   * Searches for products based on the provided query string.
   * Executes the API call in the IO dispatcher to avoid blocking the main thread.
   *
   * @param query The search query for finding products.
   * @return A list of products that match the search criteria.
   * @throws Exception if the API call fails or an error occurs.
   */
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

  /**
   * Fetches the details of a specific product by its ID.
   * Executes the API call in the IO dispatcher to avoid blocking the main thread.
   *
   * @param id The ID of the product to fetch details for.
   * @return The details of the product.
   * @throws Exception if the product is not found or if the API call fails.
   */
  suspend fun getProductById(id: String): ProductDetail {
    return withContext(Dispatchers.IO) {
      val response = apiService.getProductById(id)
      if (response.isSuccessful) {
        response.body() ?: throw Exception("Product not found")
      } else {
        throw Exception("Error fetching product by ID: ${response.errorBody()?.string()}")
      }
    }
  }
}