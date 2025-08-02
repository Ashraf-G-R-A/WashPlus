package com.example.washplus.navigation

import com.example.washplus.common.ProductType

sealed class Routes(val route: String) {
    object Home : Routes("home")
    object Sales : Routes("sales")
    object Reports : Routes("reports")
    object ProductDetails : Routes("productDetails/{productId}/{productType}") {
        fun createRoute(productId: Int, type: ProductType) =
            "productDetails/$productId/${type.name}"
    }


    object Splash : Routes("splash")
    object AddProduct : Routes("addProduct")
}
