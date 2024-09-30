package com.meli.melichallenge.data.model

data class Product(
  val id: String,
  val title: String,
  val price: Float,
  val currency_id: String,
  val thumbnail: String
)