package com.example.washplus.wash.presentaion.view

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.washplus.R
import com.example.washplus.ui.theme.WashPlusTheme

@Composable
fun WashPlusBottomNavigation(
    items: List<BottomNavigationItem>,
    selectedItem: Int,
    onItemClick: (Int) -> Unit
) {

    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = Color.White,
        contentColor = Color.White
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItem == index,
                onClick = { onItemClick(index) },
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = item.text,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.padding(4.dp))
                        Text(
                            text = item.text,
                            style = MaterialTheme.typography.labelSmall,
                        )
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    selectedTextColor = Color.White,
                    indicatorColor = Color.Black,
                    unselectedIconColor = MaterialTheme.colorScheme.onBackground,
                    unselectedTextColor = MaterialTheme.colorScheme.onBackground
                )
            )


        }
    }

}

data class BottomNavigationItem(
    @DrawableRes val icon: Int,
    val text: String
)


@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun NewsBottomNavigationPreview() {
    WashPlusTheme {
        WashPlusBottomNavigation(
            items = listOf(
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
            ),
            selectedItem = 2,
            onItemClick = {}
        )
    }
}