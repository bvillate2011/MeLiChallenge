package com.meli.melichallenge.data.api

import com.meli.melichallenge.data.ApiConstants
import com.meli.melichallenge.data.model.ProductDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import com.meli.melichallenge.data.model.ProductResponse
import retrofit2.http.Path

interface ProductApiService {

  @GET(ApiConstants.SEARCH_ENDPOINT)
  suspend fun searchProducts(@Query("q") query: String): Response<ProductResponse>

  @GET("${ApiConstants.DETAIL_ITEM}{itemId}")
  suspend fun getProductById(@Path("itemId") itemId: String): Response<ProductDetail>
}