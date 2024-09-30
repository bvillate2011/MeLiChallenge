package com.meli.melichallenge.data.api

import com.meli.melichallenge.data.ApiConstants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import com.meli.melichallenge.data.model.ProductResponse

interface ProductApiService {

  @GET(ApiConstants.SEARCH_ENDPOINT)
  suspend fun searchProducts(@Query("q") query: String): Response<ProductResponse>
}