package com.example.washplus.wash.presentaion.minview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.washplus.wash.domain.model.MapperProductDto

@Composable
fun ProductList(
    modifier: Modifier = Modifier,
    productList: List<MapperProductDto>?,
    onCardClick: (MapperProductDto) -> Unit
) {
    when {
        productList == null -> {
            ShimmerEffect()
        }

        productList.isEmpty() -> {
            EmptyScreen()
        }

        else -> {
            Column(
                modifier = modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                productList.forEach { product ->
                    ProductItemCard(
                        article = product,
                        onClick = { onCardClick(product) }
                    )
                }
            }
        }
    }
}


@Composable
private fun ShimmerEffect() {

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        repeat(10) {
            ProductCardShimmerEffect(
                modifier = Modifier.padding(8.dp)
            )
        }
    }

}