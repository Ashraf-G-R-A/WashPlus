package com.example.washplus.wash.presentaion.view

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.washplus.wash.presentaion.minview.ProductList
import com.example.washplus.wash.presentaion.viewmodel.WashPlusViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navAddProduct: () -> Unit,
    viewModel: WashPlusViewModel = hiltViewModel(),

    ) {
    val productList by viewModel.products.observeAsState(initial = emptyList())

    Scaffold(
        containerColor = Color.White,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navAddProduct() },
                containerColor = Color.Black,
                contentColor = Color.White,
                shape = FloatingActionButtonDefaults.shape,
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "إضافة منتج")
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        modifier = Modifier.background(Color.White)
    ) { padding ->

        Surface(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            color = Color.White
        ) {
            ProductList(
                productList = productList,
                onCardClick = {

                }
            )
        }
    }
}
