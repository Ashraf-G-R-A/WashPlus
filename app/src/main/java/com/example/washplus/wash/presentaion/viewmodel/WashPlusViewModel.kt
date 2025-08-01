package com.example.washplus.wash.presentaion.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.washplus.wash.data.mapper.toDomain
import com.example.washplus.wash.data.mapper.toDto
import com.example.washplus.wash.domain.model.MapperProductDto
import com.example.washplus.wash.domain.usecase.WashPlusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
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


    init {
        getAllProducts()
    }

    fun getAllProducts() {
        viewModelScope.launch {
            repository.getAllProducts()
                .map { list ->
                    list.map { it.toDomain() }
                        .sortedBy { it.count }
                }
                .collect { mappedList ->
                    _products.value = mappedList
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

