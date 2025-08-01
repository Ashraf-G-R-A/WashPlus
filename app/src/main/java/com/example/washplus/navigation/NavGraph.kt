package com.example.washplus.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.washplus.splash.SplashScreen
import com.example.washplus.wash.presentaion.view.HomeScreen


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
            HomeScreen(navAddProduct = { navController.navigate(Routes.AddProduct.route) })
        }
        composable(Routes.AddProduct.route) {

        }
        composable(Routes.Sales.route) {

        }
        composable(Routes.Reports.route) {

        }

    }


}




