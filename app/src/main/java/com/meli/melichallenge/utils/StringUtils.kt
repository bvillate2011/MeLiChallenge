package com.meli.melichallenge.utils

import android.content.Context
import android.widget.Toast
import com.meli.melichallenge.data.model.Attribute
import java.text.NumberFormat
import java.util.Locale

object StringUtils {

  fun urlForImages(input: String): String {
    return input.replace("http://", "https://")
  }

  fun formatPrice(input: Double): String {
    return NumberFormat.getNumberInstance(Locale.US).format(input)
  }

  fun getAttributes(attributes: List<Attribute>): String {
    return attributes.joinToString("\n") { attribute -> "${attribute.name}: ${attribute.value_name ?: "N/A"}" }
  }

  fun showGenericToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
  }
}