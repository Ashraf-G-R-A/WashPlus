package com.example.washplus.wash.presentaion.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.washplus.R
import com.example.washplus.common.DateUtil
import com.example.washplus.wash.domain.model.MapperProductDto
import com.example.washplus.wash.presentaion.minview.WashPlusEditText
import com.example.washplus.wash.presentaion.viewmodel.WashPlusViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductScreen(
    viewModel: WashPlusViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
) {
    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    val isValid = name.isNotBlank()
            && price.toDoubleOrNull()?.let { it > 0 } == true
            && quantity.toIntOrNull()?.let { it > 0 } == true
            && description.isNotBlank()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        CenterAlignedTopAppBar(
            title = { Text("إضافة منتج", color = Color.Black) },
            navigationIcon = {
                IconButton(onClick = { navigateBack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "رجوع",
                        tint = Color.Black
                    )
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color.White,
                titleContentColor = Color.White,
                navigationIconContentColor = Color.White
            )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            WashPlusEditText(
                title = "الاسم",
                text = name,
                des = "أدخل اسم المنتج",
                onTextChange = { name = it },
                isNumber = false
            )

            Spacer(modifier = Modifier.height(12.dp))

            WashPlusEditText(
                title = "السعر",
                text = price,
                des = "أدخل السعر (بالجنيه)",
                onTextChange = { price = it },
                isNumber = true
            )

            Spacer(modifier = Modifier.height(12.dp))

            WashPlusEditText(
                title = "العدد",
                text = quantity,
                des = "أدخل الكمية المتوفرة",
                onTextChange = { quantity = it },
                isNumber = true
            )

            Spacer(modifier = Modifier.height(12.dp))

            WashPlusEditText(
                title = "الوصف",
                text = description,
                des = "أدخل وصفًا موجزًا",
                onTextChange = { description = it },
                isNumber = false
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    val product = MapperProductDto(
                        id = 0,
                        title = name,
                        description = description,
                        price = price.toDouble(),
                        count = quantity.toInt(),
                        date = DateUtil.getCurrentDate()
                    )
                    viewModel.addProduct(product)
                    navigateBack()
                },
                enabled = isValid,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isValid) MaterialTheme.colorScheme.primary
                    else colorResource(R.color.shimmer),
                    contentColor = Color.White
                )
            ) {
                Text("إضافة المنتج")
            }
        }
    }
}
