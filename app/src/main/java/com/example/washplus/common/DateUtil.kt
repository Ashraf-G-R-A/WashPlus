package com.example.washplus.common

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {
    fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale("ar"))
        dateFormat.timeZone = TimeZone.getDefault()
        return dateFormat.format(Date())
    }
}
