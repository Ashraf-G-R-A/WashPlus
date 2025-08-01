package com.example.washplus.wash.presentaion.minview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.washplus.R


@Composable
fun WashPlusEditText(
    title: String,
    text: String,
    des: String,
    onTextChange: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        TextField(
            value = text,
            onValueChange = onTextChange,
            placeholder = { Text(des) },
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colorResource(R.color.gray50),
                    shape = RoundedCornerShape(8.dp)
                ),
            singleLine = true,
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = colorResource(R.color.gray50),
                unfocusedContainerColor = colorResource(R.color.gray50),
                disabledContainerColor = colorResource(R.color.gray50),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )

    }
}

@Preview(showBackground = true)
@Composable
fun WashPlusEditTextPreview() {
    var text by remember { mutableStateOf("") }

    WashPlusEditText(
        title = "Name",
        text = text,
        des = "Put down a short title of your game",
        onTextChange = { text = it }
    )
}


