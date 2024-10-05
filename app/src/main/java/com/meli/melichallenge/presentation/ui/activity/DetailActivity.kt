package com.meli.melichallenge.presentation.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import coil.compose.rememberAsyncImagePainter
import com.meli.melichallenge.R
import com.meli.melichallenge.data.model.ProductDetail
import com.meli.melichallenge.presentation.viewmodel.ProductDetailViewModel
import com.meli.melichallenge.utils.StringUtils.formatPrice
import com.meli.melichallenge.utils.StringUtils.getAttributes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

  private val productDetailViewModel: ProductDetailViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_detail)

    val productTitleTextView = findViewById<TextView>(R.id.productTitle)
    val productPriceTextView = findViewById<TextView>(R.id.productPrice)
    val productFeaturesTextView = findViewById<TextView>(R.id.productFeatures)
    val composeView = findViewById<ComposeView>(R.id.productImagePager)

    val productCode = intent.getStringExtra("PRODUCT_CODE")

    productCode?.let {
      productDetailViewModel.getProductById(it)
    }

    productDetailViewModel.productDetail.observe(this, Observer { productDetail ->
      productTitleTextView.text = productDetail.title

      composeView.setContent {
        ProductDetailView(productDetail)
      }

      productPriceTextView.text = formatPrice(productDetail.price)
      productFeaturesTextView.text = getAttributes(productDetail.attributes)
    })

    productDetailViewModel.loading.observe(this, Observer { isLoading ->
      Log.d("DetailActivity", "Cargando: $isLoading")
    })

    productDetailViewModel.error.observe(this, Observer { errorMessage ->
      errorMessage?.let {
        Log.e("DetailActivity", "Error: $it")
      }
    })
  }

  @Composable
  fun ProductDetailView(productDetail: ProductDetail) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFF5F5F5))
        .padding(16.dp)
    ) {
      LazyRow {
        items(productDetail.pictures) { product ->
          Image(
            painter = rememberAsyncImagePainter(model = product.secure_url),
            contentDescription = null,
            modifier = Modifier
              .size(400.dp)
              .padding(end = 8.dp),
            contentScale = ContentScale.Crop
          )
        }
      }
    }
  }
}