package com.meli.melichallenge.presentation.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.meli.melichallenge.presentation.ui.screen.SearchScreen
import com.meli.melichallenge.presentation.viewmodel.ProductViewModel
import com.meli.melichallenge.ui.theme.MeLiChallengeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  private val viewModel: ProductViewModel by viewModels()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      MeLiChallengeTheme {
        SearchScreen(viewModel = viewModel)
      }
    }
  }
}
