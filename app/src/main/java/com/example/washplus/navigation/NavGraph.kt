package com.example.washplus.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.washplus.common.ProductType
import com.example.washplus.splash.SplashScreen
import com.example.washplus.wash.presentaion.view.AddProductScreen
import com.example.washplus.wash.presentaion.view.HomeScreen
import com.example.washplus.wash.presentaion.view.ProductDetailsScreen
import com.example.washplus.wash.presentaion.view.ReportScreen
import com.example.washplus.wash.presentaion.view.SealsScreen


@Composable
fun AppNavGraph(navController: NavHostController, paddingValues: PaddingValues) {


    NavHost(
        navController = navController, startDestination = Routes.Splash.route,
        modifier = Modifier.padding(paddingValues)
    ) {
        composable(Routes.Splash.route) {
            SplashScreen(navHome = { navController.navigate(Routes.Home.route) })
        }
        composable(Routes.Home.route) {
            HomeScreen(
                navAddProduct = { navController.navigate(Routes.AddProduct.route) },
                navProductDetails = { productId ->
                    navController.navigate(
                        Routes.ProductDetails.createRoute(productId, ProductType.PRODUCT)
                    )
                })

        }
        composable(Routes.AddProduct.route) {
            AddProductScreen(navigateBack = { navController.popBackStack() })

        }
        composable(Routes.Sales.route) {
            SealsScreen { productId ->
                navController.navigate(
                    Routes.ProductDetails.createRoute(productId, ProductType.SALE)
                )

            }

        }
        composable(Routes.Reports.route) {
            ReportScreen()
        }

        composable(Routes.ProductDetails.route) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("productId")?.toIntOrNull()
            val typeString = backStackEntry.arguments?.getString("productType")
            val type = try {
                ProductType.valueOf(typeString ?: "")
            } catch (e: Exception) {
                null
            }

            if (id != null && type != null) {
                ProductDetailsScreen(
                    productId = id,
                    productType = type,
                    navigateBack = { navController.popBackStack() }
                )
            }
        }


    }


}




