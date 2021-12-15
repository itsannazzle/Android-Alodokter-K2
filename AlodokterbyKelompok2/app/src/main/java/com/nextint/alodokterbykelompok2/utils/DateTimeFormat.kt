package com.nextint.alodokterbykelompok2.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateTimeFormat {
    fun formatDate(date: String, format: String = "dd MMM yyyy"): String{
        var formattedDate = ""
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        sdf.timeZone = TimeZone.getTimeZone("UTC")

        try {
            val parseDate = sdf.parse(date)
            formattedDate = SimpleDateFormat(format).format(parseDate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return formattedDate
    }
}