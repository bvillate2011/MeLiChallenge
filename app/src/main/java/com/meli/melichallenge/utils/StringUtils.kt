package com.meli.melichallenge.utils

import com.meli.melichallenge.data.model.Attribute
import java.text.NumberFormat
import java.util.Locale

object StringUtils {
  fun urlForImages(input: String): String {
    // Implementación de formateo
    return input.replace("http://", "https://")
  }

  fun formatPrice(input: Double): String {
    // Implementación de formateo
    return NumberFormat.getNumberInstance(Locale.US).format(input)
  }

  fun getAttributes(attributes: List<Attribute>): String {
    // Implementación de formateo
    return attributes.joinToString("\n") { attribute -> "${attribute.name}: ${attribute.value_name ?: "N/A"}" }
  }
}