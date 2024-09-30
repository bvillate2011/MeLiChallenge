package com.meli.melichallenge.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.meli.melichallenge.presentation.viewmodel.ProductViewModel
import androidx.compose.material3.Divider

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
          keyboardController?.hide() // Ocultar teclado cuando se presiona "Done"
          viewModel.searchProducts(query) // Realizar búsqueda
        }
      )
    )

    Spacer(modifier = Modifier.height(16.dp))

    Button(onClick = {
      keyboardController?.hide() // Ocultar teclado cuando se hace clic en el botón
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
        Column {
          Text(product.title)
          Spacer(modifier = Modifier.height(8.dp))
          HorizontalDivider(
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f),
            thickness = 1.dp
          )
        }
      }
    }
  }
}