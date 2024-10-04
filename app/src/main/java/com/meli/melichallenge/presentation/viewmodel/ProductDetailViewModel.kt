package com.meli.melichallenge.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meli.melichallenge.data.model.ProductDetail
import com.meli.melichallenge.domain.usecase.GetProductByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
  private val getProductByIdUseCase: GetProductByIdUseCase
) : ViewModel() {

  private val _productDetail = MutableLiveData<ProductDetail>()
  val productDetail: LiveData<ProductDetail> get() = _productDetail

  private val _loading = MutableLiveData<Boolean>()
  val loading: LiveData<Boolean> get() = _loading

  private val _error = MutableLiveData<String?>()
  val error: LiveData<String?> get() = _error

  // Función para obtener los detalles de un producto por ID
  fun getProductById(id: String) {
    viewModelScope.launch {
      _loading.value = true
      try {
        val result = getProductByIdUseCase(id)
        _productDetail.value = result
        _error.value = null
      } catch (e: Exception) {
        _error.value = "Error fetching product details: ${e.message}"
      } finally {
        _loading.value = false
      }
    }
  }
}