package com.example.washplus.navigation

sealed class Routes(val route: String) {
    object Home : Routes("home")
    object Sales : Routes("sales")
    object Reports : Routes("reports")
    object ProductDetails : Routes("productDetails")
    object Splash : Routes("splash")
    object AddProduct : Routes("addProduct")
}
