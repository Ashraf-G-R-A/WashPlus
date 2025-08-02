package com.example.washplus.wash.presentaion.view

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.washplus.common.ProductType
import com.example.washplus.wash.data.mapper.toDto
import com.example.washplus.wash.presentaion.viewmodel.WashPlusViewModel

@Composable
fun ProductDetailsScreen(
    navigateBack: () -> Unit,
    productId: Int,
    productType: ProductType,
    viewModel: WashPlusViewModel = hiltViewModel(),
) {
    val productDetails by viewModel.selectedProduct.observeAsState()
    val isLoading by viewModel.isLoading.observeAsState(false)

    LaunchedEffect(productId, productType) {
        when (productType) {
            ProductType.PRODUCT -> viewModel.getProductById(productId)
            ProductType.SALE -> viewModel.getSaleProductById(productId)
        }
    }
    when {
        isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("جاري تحميل التفاصيل...")
            }
        }

        productDetails == null -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("المنتج غير موجود أو لم يتم تحميله.")
            }
        }

        else -> {
            val product = productDetails!!
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "حذف",
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                if (productType == ProductType.PRODUCT) {
                                    viewModel.deleteProduct(product.id)
                                } else {
                                    viewModel.deleteSaleProduct(product.id)
                                }
                                navigateBack()
                            }
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Text(
                        text = "تفاصيل المنتج",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.width(12.dp))
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "رجوع",
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { navigateBack() }
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .background(Color(0xFFF7F7F7), RoundedCornerShape(12.dp))
                        .padding(16.dp)
                ) {
                    Text(
                        text = product.title,
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = product.description,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Box(
                            modifier = Modifier
                                .border(1.dp, Color.Gray, RoundedCornerShape(6.dp))
                                .padding(horizontal = 16.dp, vertical = 6.dp)
                        ) {
                            Text(
                                product.count.toString(),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }

                        Text(
                            text = "${product.price} ج.م",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                if (productType == ProductType.PRODUCT) {
                    Button(
                        onClick = {
                            viewModel.payProduct(product.toDto())
                            navigateBack()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .padding(horizontal = 16.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                    ) {
                        Text(
                            text = "اشتري المنتج",
                            style = MaterialTheme.typography.bodyMedium.copy(color = Color.Black)
                        )
                    }
                }

            }
        }
    }
}
