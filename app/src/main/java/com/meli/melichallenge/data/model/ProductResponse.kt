package com.meli.melichallenge.data.model

data class ProductResponse(
  val site_id: String,
  val query: String,
  val paging: Paging,
  val results: List<Product>
)

data class Paging(
  val total: Int,
  val primary_results: Int,
  val offset: Int,
  val limit: Int
)
