package com.meli.melichallenge.data.model

data class ProductDetail (
  val id: String,
  val site_id: String,
  val title: String,
  val seller_id: Double,
  val category_id: String,
  val official_store_id: Any? = null,
  val price: Double,
  val base_price: Double,
  val original_price: Any? = null,
  val currency_id: String,
  val initial_quantity: Double,
  val sale_terms: List<SaleTerm>,
  val buying_mode: String,
  val listing_type_id: String,
  val condition: String,
  val permalink: String,
  val thumbnail_id: String,
  val thumbnail: String,
  val pictures: List<Picture>,
  val video_id: Any? = null,
  val descriptions: List<Any?>,
  val accepts_mercadopago: Boolean,
  val non_mercado_pago_payment_methods: List<Any?>,
  val shipping: Shipping,
  val international_delivery_mode: String,
  val seller_address: SellerAddress,
  val seller_contact: Any? = null,
  val location: Location,
  val coverage_areas: List<Any?>,
  val attributes: List<Attribute>,
  val listing_source: String,
  val variations: List<Variation>,
  val status: String,
  val sub_status: List<Any?>,
  val tags: List<String>,
  val warranty: String,
  val catalog_product_id: Any? = null,
  val domain_id: String,
  val parent_item_id: Any? = null,
  val deal_ids: List<Any?>,
  val automatic_relist: Boolean,
  val date_created: String,
  val last_updated: String,
  val health: Double,
  val catalog_listing: Boolean
)

data class Attribute (
  val id: String,
  val name: String,
  val value_id: String? = null,
  val value_name: String? = null,
  val values: List<Value>,
  val value_type: String
)

data class Value (
  val id: String? = null,
  val name: String? = null,
  val struct: Struct? = null
)

data class Struct (
  val number: Double,
  val unit: String
)

class Location()

data class Picture (
  val id: String,
  val url: String,
  val secure_url: String,
  val size: String,
  val max_size: String,
  val quality: String
)

data class SaleTerm (
  val id: String,
  val name: String,
  val value_id: String? = null,
  val value_name: String,
  val value_struct: Struct? = null,
  val values: List<Value>,
  val value_type: String
)

data class SellerAddress (
  val city: City,
  val state: City,
  val country: City,
  val search_location: SearchLocation,
  val id: Double
)

data class City (
  val id: String,
  val name: String
)

data class SearchLocation (
  val city: City,
  val state: City
)

data class Shipping (
  val mode: String,
  val methods: List<Any?>,
  val tags: List<Any?>,
  val dimensions: Any? = null,
  val local_pick_up: Boolean,
  val free_shipping: Boolean,
  val logistic_type: String,
  val store_pick_up: Boolean
)

data class Variation (
  val id: Double,
  val price: Double,
  val attribute_combinations: List<Attribute>,
  val sale_terms: List<Any?>,
  val picture_ids: List<String>,
  val catalog_product_id: Any? = null
)
