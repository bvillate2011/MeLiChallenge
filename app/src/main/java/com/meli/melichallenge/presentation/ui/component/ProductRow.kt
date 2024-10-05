package com.meli.melichallenge.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.meli.melichallenge.data.model.Product
import com.meli.melichallenge.utils.StringUtils.formatPrice
import com.meli.melichallenge.utils.StringUtils.urlForImages

@Composable
fun ProductRow(product: Product, onClick: () -> Unit) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .height(90.dp)
      .padding(horizontal = 8.dp, vertical = 4.dp)
      .clip(RoundedCornerShape(8.dp))
      .background(Color(0xFFF5F5F5))
      .clickable { onClick() }
      .padding(8.dp),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
  ) {
    Image(
      painter = rememberAsyncImagePainter(urlForImages(product.thumbnail)),
      contentDescription = product.title,
      modifier = Modifier
        .size(64.dp)
        .aspectRatio(1f)
        .padding(start = 8.dp),
      contentScale = ContentScale.Crop
    )
    Column(
      modifier = Modifier.weight(1f)
        .padding(start = 16.dp)
    ) {
      Text(
        text = if (product.title.length > 100) product.title.take(100) else product.title,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.fillMaxWidth(),
        fontSize = 14.sp
      )
      Spacer(modifier = Modifier.height(4.dp))
      Text(
        text = "${product.currency_id} ${formatPrice(product.price.toDouble())}",
        fontSize = 16.sp, // Tamaño de texto más grande
        fontWeight = FontWeight.Bold, // Texto en negrita
      )
      Spacer(modifier = Modifier.height(8.dp))
    }
  }
}