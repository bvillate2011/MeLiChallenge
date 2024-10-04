package com.meli.melichallenge.domain.usecase

import com.meli.melichallenge.data.api.ProductApiService
import com.meli.melichallenge.data.model.Attribute
import com.meli.melichallenge.data.model.City
import com.meli.melichallenge.data.model.Location
import com.meli.melichallenge.data.model.Picture
import com.meli.melichallenge.data.model.ProductDetail
import com.meli.melichallenge.data.model.SaleTerm
import com.meli.melichallenge.data.model.SearchLocation
import com.meli.melichallenge.data.model.SellerAddress
import com.meli.melichallenge.data.model.Shipping
import com.meli.melichallenge.data.model.Value
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
class GetProductByIdUseCaseTest {

  private lateinit var apiService: ProductApiService
  private lateinit var productRepository: ProductRepository
  private lateinit var getProductByIdUseCase: GetProductByIdUseCase

  @Before
  fun setUp() {

    apiService = mock(ProductApiService::class.java)

    productRepository = ProductRepository(apiService)

    getProductByIdUseCase = GetProductByIdUseCase(productRepository)
  }

  @Test
  fun invoke_should_throw_error() = runTest {

    val productId = "123"
    val errorMessage = "Error fetching product by ID"

    `when`(apiService.getProductById(productId)).thenThrow(RuntimeException(errorMessage))

    try {
      getProductByIdUseCase(productId)
      fail("Expected RuntimeException was not thrown")
    } catch (e: RuntimeException) {
      assertEquals(errorMessage, e.message)
    }
  }

  @Test
  fun invoke_should_return_product_detail_successfully() = runTest {

    val productId = "123"
    val expectedProductDetail = ProductDetail(
      id = productId,
      site_id = "MLA",
      title = "Sample Product",
      seller_id = 12345.0,
      category_id = "MLA1234",
      official_store_id = null,
      price = 1000.0,
      base_price = 1000.0,
      original_price = null,
      currency_id = "ARS",
      initial_quantity = 10.0,
      sale_terms = listOf(
        SaleTerm(
          id = "SALE_TERM_ID",
          name = "Garantía",
          value_name = "6 months warranty",
          values = listOf(Value(name = "6 months")),
          value_type = "STRING"
        )
      ),
      buying_mode = "buy_it_now",
      listing_type_id = "gold_special",
      condition = "new",
      permalink = "http://example.com",
      thumbnail_id = "12345-MLA",
      thumbnail = "http://example.com/thumbnail.jpg",
      pictures = listOf(
        Picture(
          id = "pic1",
          url = "http://example.com/pic1.jpg",
          secure_url = "https://example.com/pic1.jpg",
          size = "500x500",
          max_size = "1000x1000",
          quality = "high"
        )
      ),
      video_id = null,
      descriptions = emptyList(),
      accepts_mercadopago = true,
      non_mercado_pago_payment_methods = emptyList(),
      shipping = Shipping(
        mode = "me2",
        methods = emptyList(),
        tags = emptyList(),
        dimensions = null,
        local_pick_up = false,
        free_shipping = true,
        logistic_type = "cross_docking",
        store_pick_up = false
      ),
      international_delivery_mode = "none",
      seller_address = SellerAddress(
        city = City(id = "1234", name = "Buenos Aires"),
        state = City(id = "123", name = "Buenos Aires Province"),
        country = City(id = "AR", name = "Argentina"),
        search_location = SearchLocation(
          city = City(id = "1234", name = "Buenos Aires"),
          state = City(id = "123", name = "Buenos Aires Province")
        ),
        id = 56789.0
      ),
      seller_contact = null,
      location = Location(),
      coverage_areas = emptyList(),
      attributes = listOf(
        Attribute(
          id = "COLOR",
          name = "Color",
          value_name = "Red",
          values = listOf(Value(name = "Red")),
          value_type = "STRING"
        )
      ),
      listing_source = "classic",
      variations = emptyList(),
      status = "active",
      sub_status = emptyList(),
      tags = listOf("brand_verified"),
      warranty = "6 months warranty",
      catalog_product_id = null,
      domain_id = "MLA12345",
      parent_item_id = null,
      deal_ids = emptyList(),
      automatic_relist = true,
      date_created = "2022-01-01T00:00:00Z",
      last_updated = "2022-01-02T00:00:00Z",
      health = 0.8,
      catalog_listing = false
    )

    `when`(apiService.getProductById(productId)).thenReturn(Response.success(expectedProductDetail))

    val result = getProductByIdUseCase(productId)

    assertEquals(expectedProductDetail, result)

    verify(apiService).getProductById(productId)
  }
}