package com.example.washplus.wash.presentaion.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.washplus.wash.data.mapper.toDomain
import com.example.washplus.wash.data.mapper.toDto
import com.example.washplus.wash.domain.model.MapperProductDto
import com.example.washplus.wash.domain.usecase.WashPlusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WashPlusViewModel @Inject constructor(
    private val repository: WashPlusUseCase
) : ViewModel() {

    private val _products = MutableLiveData<List<MapperProductDto>>(emptyList())
    val products: LiveData<List<MapperProductDto>> = _products

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _selectedProduct = MutableLiveData<MapperProductDto?>()
    val selectedProduct: LiveData<MapperProductDto?> = _selectedProduct

    fun getAllProducts() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val products = repository.getAllProducts()
                _products.value = products.map { it.toDomain() }
            } catch (e: Exception) {
                _products.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun addProduct(productDto: MapperProductDto) {
        viewModelScope.launch {
            repository.addProduct(productDto.toDto())
            getAllProducts()
        }
    }

    fun deleteProduct(productDto: MapperProductDto) {
        viewModelScope.launch {
            repository.deleteProduct(productDto.toDto())
            getAllProducts()
        }
    }

    fun updateProduct(productDto: MapperProductDto) {
        viewModelScope.launch {
            repository.updateProduct(productDto.toDto())
            getAllProducts()
        }
    }

    fun getProductById(id: Int) {
        viewModelScope.launch {
            try {
                val result = repository.getProductById(id)
                _selectedProduct.value = result.toDomain()
            } catch (e: Exception) {
                _selectedProduct.value = null
            }
        }
    }

    fun payProduct(id: Int) {
        viewModelScope.launch {
            repository.payProduct(id)
            getAllProducts()
        }
    }
}

