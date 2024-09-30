package com.meli.melichallenge.data.api

import com.meli.melichallenge.data.ApiConstants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiModule {

  private val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(ApiConstants.BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

  val productApiService: ProductApiService = retrofit.create(ProductApiService::class.java)
}