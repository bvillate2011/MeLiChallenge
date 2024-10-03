package com.meli.melichallenge.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.meli.melichallenge.presentation.viewmodel.ProductViewModel

@Composable
fun SearchScreen(viewModel: ProductViewModel = viewModel()) {
  var query by remember { mutableStateOf("") }
  val keyboardController = LocalSoftwareKeyboardController.current

  Column(modifier = Modifier.padding(16.dp)) {

    Spacer(modifier = Modifier.height(32.dp))

    TextField(
      value = query,
      onValueChange = { query = it },
      label = { Text("Buscar productos") },
      modifier = Modifier.fillMaxWidth(),
      keyboardOptions = KeyboardOptions.Default.copy(
        imeAction = ImeAction.Done
      ),
      keyboardActions = KeyboardActions(
        onDone = {
          keyboardController?.hide()
          viewModel.searchProducts(query)
        }
      )
    )

    Spacer(modifier = Modifier.height(16.dp))

    Button(onClick = {
      keyboardController?.hide()
      viewModel.searchProducts(query)
    }, modifier = Modifier.fillMaxWidth()) {
      Text("Buscar")
    }

    val products by viewModel.products.observeAsState(emptyList())
    val loading by viewModel.loading.observeAsState(false)
    val error by viewModel.error.observeAsState()

    if (loading) {
      CircularProgressIndicator(modifier = Modifier.padding(16.dp))
    }

    error?.let {
      Text(text = it, color = Color.Red)
    }

    LazyColumn {
      items(products) { product ->
        ProductRow(product = product)
      }
    }
  }
}