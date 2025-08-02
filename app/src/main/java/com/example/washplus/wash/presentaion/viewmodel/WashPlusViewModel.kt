package com.example.washplus.wash.presentaion.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.washplus.wash.data.mapper.toDomain
import com.example.washplus.wash.data.mapper.toDto
import com.example.washplus.wash.data.model.ProductDto
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

    private val _selectedProduct = MutableLiveData<MapperProductDto>()
    val selectedProduct: LiveData<MapperProductDto> = _selectedProduct

    private val _salesProducts = MutableLiveData<List<MapperProductDto>>(emptyList())
    val salesProducts: LiveData<List<MapperProductDto>> = _salesProducts


    init {
        getAllProducts()
        getSaleProducts()
    }

    fun getAllProducts() {
        viewModelScope.launch {
            _isLoading.value = true
            repository.getAllProducts()
                .map { list ->
                    list.map { it.toDomain() }
                        .sortedBy { it.count }
                }
                .collect { mappedList ->
                    _products.value = mappedList
                    _isLoading.value = false
                }
        }
    }


    fun addProduct(productDto: MapperProductDto) {
        viewModelScope.launch {
            _isLoading.value = true
            repository.addProduct(productDto.toDto())
            getAllProducts()
            _isLoading.value = false
        }
    }

    fun deleteProduct(productId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            repository.deleteProduct(productId)
            getAllProducts()
            _isLoading.value = false
        }
    }

    fun updateProduct(productDto: MapperProductDto) {
        viewModelScope.launch {
            _isLoading.value = true
            repository.updateProduct(productDto.toDto())
            getAllProducts()
            _isLoading.value = false
        }
    }

    fun getProductById(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            val product = repository.getProductById(id)
            _selectedProduct.value = product.toDomain()
            Log.d("VM", "Product loaded: ${_selectedProduct.value}")
            _isLoading.value = false
        }
    }

    fun payProduct(productDto: ProductDto) {
        viewModelScope.launch {
            _isLoading.value = true
            repository.payProduct(productDto)
            getAllProducts()
            getSaleProducts()
            _isLoading.value = false
        }
    }

    fun getSaleProducts() {
        viewModelScope.launch {
            _isLoading.value = true
            repository.getAllSales()
                .map { list ->
                    list.map { it.toDomain() }.sortedBy {
                        it.count
                    }
                }
                .collect { mappedList ->
                    _salesProducts.value = mappedList
                    _isLoading.value = false
                }
        }
    }

    fun getSaleProductById(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            val product = repository.getSaleById(id)
            _selectedProduct.value = product.toDomain()
            _isLoading.value = false
        }
    }

    fun deleteSaleProduct(productId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            repository.deleteSaleProduct(productId)
            getSaleProducts()
            _isLoading.value = false
        }

    }
}

