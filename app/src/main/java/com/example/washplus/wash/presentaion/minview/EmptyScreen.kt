package com.example.washplus.wash.presentaion.minview

import android.content.res.Configuration
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.washplus.R
import coil.network.HttpException
import java.io.IOException

@Composable
fun EmptyScreen(error: com.example.washplus.common.Result.Failure? = null) {

    val message = error?.let { parseErrorMessage(it) } ?: "No data available"

    var startAnimation by remember {
        mutableStateOf(false)
    }

    val alphaAnimation by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(durationMillis = 1000), label = ""
    )

    LaunchedEffect(Unit) {
        startAnimation = true
    }

    ErrorContent(
        alphaAnim = alphaAnimation,
        message = message,
        icon = R.drawable.ic_network_error
    )
}


@Composable
fun ErrorContent(alphaAnim: Float, message: String, icon: Int) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = androidx.compose.ui.res.painterResource(id = icon),
            contentDescription = null,
            tint = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray,

            modifier = Modifier
                .size(120.dp)
                .alpha(alphaAnim)
        )
        Text(
            text = message,
            color = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray,
            modifier = Modifier
                .padding(10.dp)
                .alpha(alphaAnim),
            style = androidx.compose.material3.MaterialTheme.typography.bodyMedium
        )

    }

}

fun parseErrorMessage(error:  com.example.washplus.common.Result.Failure): String {
    return when (val e = error.error) {
        is IOException -> "No Internet Connection"
        is HttpException -> "Server Unavailable"
        else -> "Unknown Error"
    }
}


@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun EmptyScreenPreview() {
    ErrorContent(
        alphaAnim = 0.3f,
        message = "Server Unavailable",
        icon = R.drawable.ic_network_error
    )
}


