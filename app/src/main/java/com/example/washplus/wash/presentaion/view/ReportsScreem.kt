package com.example.washplus.wash.presentaion.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.washplus.wash.data.mapper.toDomain
import com.example.washplus.wash.presentaion.minview.ProductList
import com.example.washplus.wash.presentaion.viewmodel.WashPlusViewModel

@Composable
fun ReportScreen(viewModel: WashPlusViewModel = hiltViewModel()) {
    val reports by viewModel.reports.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        viewModel.loadReports()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        reports.forEach { yearReport ->
            item {
                Text(
                    text = "السنة: ${yearReport.year}",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            yearReport.months.forEach { monthReport ->
                item {
                    Text(
                        text = "  الشهر: ${monthReport.month}",
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }

                items(monthReport.sales) { sale ->
                    ProductList(
                        productList = listOf(sale.toDomain()),
                        onCardClick = { /* handle click */ }
                    )
                }
            }
        }
    }

}
