package com.example.washplus.wash.presentaion.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.washplus.R
import com.example.washplus.navigation.AppNavGraph
import com.example.washplus.navigation.Routes

@Composable
fun WashPlusNavigation() {
    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(
                icon = R.drawable.ic_home,
                text = "الرئسية"
            ),
            BottomNavigationItem(
                icon = R.drawable.ic_sales,
                text = "المبيعات"
            ),
            BottomNavigationItem(
                icon = R.drawable.ic_reports,
                text = "التقارير"
            ),
        )
    }

    val navController = rememberNavController()
    val backstackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable { mutableIntStateOf(0) }

    selectedItem = when (backstackState?.destination?.route) {
        Routes.Home.route -> 0
        Routes.Sales.route -> 1
        Routes.Reports.route -> 2
        else -> 0
    }

    val isSplash = backstackState?.destination?.route == Routes.Splash.route

    Scaffold(
        bottomBar = {
            if (!isSplash) {
                WashPlusBottomNavigation(
                    items = bottomNavigationItems,
                    selectedItem = selectedItem,
                ) { index ->
                    when (index) {
                        0 -> navigationToTop(navController, Routes.Home.route)
                        1 -> navigationToTop(navController, Routes.Sales.route)
                        2 -> navigationToTop(navController, Routes.Reports.route)
                    }
                }
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        AppNavGraph(navController = navController, paddingValues = paddingValues)
    }
}

fun navigationToTop(navController: NavController, route: String) {
    navController.navigate(route) {
        popUpTo(navController.graph.startDestinationId) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }

}
