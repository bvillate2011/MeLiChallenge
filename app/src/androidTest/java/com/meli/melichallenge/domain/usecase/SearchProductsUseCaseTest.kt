package com.meli.melichallenge.domain.usecase

import com.meli.melichallenge.data.api.ProductApiService
import com.meli.melichallenge.data.model.Paging
import com.meli.melichallenge.data.model.Product
import com.meli.melichallenge.data.model.ProductResponse
import com.meli.melichallenge.data.repository.ProductRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import retrofit2.Response

@ExperimentalCoroutinesApi
class SearchProductsUseCaseTest {

  private lateinit var apiService: ProductApiService
  private lateinit var productRepository: ProductRepository
  private lateinit var searchProductsUseCase: SearchProductsUseCase

  @Before
  fun setUp() {
    apiService = mock(ProductApiService::class.java)

    productRepository = ProductRepository(apiService)

    searchProductsUseCase = SearchProductsUseCase(productRepository)
  }

  @Test
  fun invoke_should_throw_error() = runTest {

    val productQuery = "test"
    val errorMessage = "Error fetching products"

    `when`(apiService.searchProducts(productQuery)).thenThrow(RuntimeException(errorMessage))

    try {
      searchProductsUseCase(productQuery)
      fail("Expected RuntimeException was not thrown")
    } catch (e: RuntimeException) {
      assertEquals(errorMessage, e.message)
    }
  }

  @Test
  fun invoke_should_return_products_successfully() = runTest {

    val query = "Sample Product"
    val expectedProducts = listOf(
      Product(
        id = "123",
        title = "Sample Product 1",
        price = 1000.0F,
        currency_id = "ARS",
        thumbnail = "http://example.com/thumbnail1.jpg"
      ),
      Product(
        id = "456",
        title = "Sample Product 2",
        price = 1000.0F,
        currency_id = "USD",
        thumbnail = "http://example.com/thumbnail2.jpg"
      )
    )
    val expectedPaging = Paging(
      0,
      0,
      0,
      0
    )
    val productResponse = ProductResponse(results = expectedProducts, site_id = "", query = "", paging = expectedPaging)

    `when`(apiService.searchProducts(query)).thenReturn(Response.success(productResponse))

    val result = searchProductsUseCase(query)

    assertEquals(expectedProducts, result)
    assertEquals(2, result.size)

    verify(apiService).searchProducts(query)
  }
}