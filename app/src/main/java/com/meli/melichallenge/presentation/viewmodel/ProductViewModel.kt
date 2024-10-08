package com.meli.melichallenge.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meli.melichallenge.data.model.Product
import com.meli.melichallenge.domain.usecase.SearchProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val searchProductsUseCase: SearchProductsUseCase) : ViewModel() {

  // LiveData holding the list of products retrieved from the search
  private val _products = MutableLiveData<List<Product>>()
  val products: LiveData<List<Product>> get() = _products

  // LiveData indicating if the search operation is in progress
  private val _loading = MutableLiveData<Boolean>()
  val loading: LiveData<Boolean> get() = _loading

  // LiveData containing error messages if an issue occurs during the search
  private val _error = MutableLiveData<String?>()
  val error: LiveData<String?> get() = _error

  /**
   * Executes the product search based on the provided query.
   * Updates the loading state and handles potential errors.
   *
   * @param query The search term.
   */
  fun searchProducts(query: String) {
    viewModelScope.launch {
      _loading.value = true
      try {
        val result = searchProductsUseCase(query)
        _products.value = result
        _error.value = null
      } catch (e: Exception) {
        _error.value = "Error fetching products: ${e.message}"
      } finally {
        _loading.value = false
      }
    }
  }
}