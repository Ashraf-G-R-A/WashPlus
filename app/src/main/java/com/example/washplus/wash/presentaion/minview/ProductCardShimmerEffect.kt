package com.example.washplus.wash.presentaion.minview

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.washplus.common.shimmerEffect

@Composable
fun ProductCardShimmerEffect(modifier: Modifier = Modifier) {

    Row(
        modifier = modifier
            .clickable(enabled = false) { }
    ) {

        Box(
            modifier = Modifier
                .size(96.dp)
                .padding(6.dp)
                .shimmerEffect()
                .background(
                    Color.LightGray,
                    shape = RoundedCornerShape(8.dp)
                )
        )

        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(horizontal = 3.dp)
                .height(96.dp)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .shimmerEffect()
                    .background(Color.LightGray, shape = RoundedCornerShape(4.dp))
            )

            Row(verticalAlignment = Alignment.CenterVertically) {

                Box(
                    modifier = Modifier
                        .width(60.dp)
                        .height(16.dp)
                        .shimmerEffect()
                        .background(Color.LightGray, shape = RoundedCornerShape(4.dp))
                )

                Spacer(modifier = Modifier.width(8.dp))


                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .shimmerEffect()
                        .background(Color.LightGray, shape = CircleShape)
                )

                Spacer(modifier = Modifier.width(4.dp))


                Box(
                    modifier = Modifier
                        .width(60.dp)
                        .padding(start = 4.dp)
                        .height(16.dp)
                        .shimmerEffect()
                        .background(Color.LightGray, shape = RoundedCornerShape(4.dp))
                )
            }
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true)
@Composable
fun NewsCardShimmerEffectDarkPreview() {

}
