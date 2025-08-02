package com.example.washplus.wash.presentaion.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.washplus.wash.presentaion.minview.ProductList
import com.example.washplus.wash.presentaion.viewmodel.WashPlusViewModel


@Composable
fun SealsScreen(
    viewModel: WashPlusViewModel = hiltViewModel(),
    navProductDetails: (Int) -> Unit
) {
    val productList by viewModel.salesProducts.observeAsState(initial = emptyList())

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color.White
    ) {
        ProductList(
            productList = productList,
            onCardClick = {
                navProductDetails(it.id)
            }
        )
    }
}