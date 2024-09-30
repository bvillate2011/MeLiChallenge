package com.meli.melichallenge.data.repository

import com.meli.melichallenge.data.api.ProductApiService
import com.meli.melichallenge.data.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductRepository(private val apiService: ProductApiService) {

  // Función para buscar productos usando la API de Mercado Libre
  suspend fun searchProducts(query: String): List<Product> {
    return withContext(Dispatchers.IO) {  // Ejecutar en un hilo de I/O
      val response = apiService.searchProducts(query)
      if (response.isSuccessful) {
        // Si la respuesta es exitosa, devolver la lista de productos
        response.body()?.results ?: emptyList()
      } else {
        // Si ocurre un error, lanzar una excepción
        throw Exception("Error fetching products: ${response.errorBody()?.string()}")
      }
    }
  }
}