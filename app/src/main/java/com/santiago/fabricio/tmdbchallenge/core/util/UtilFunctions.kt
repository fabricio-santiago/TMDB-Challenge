package com.santiago.fabricio.tmdbchallenge.core.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

object UtilFunctions {

    @RequiresApi(Build.VERSION_CODES.O)
    fun convertDate(dateString: String, targetFormat: String, locale: Locale = Locale.getDefault()): String {
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val date = LocalDate.parse(dateString, inputFormatter)
        val outputFormatter = DateTimeFormatter.ofPattern(targetFormat, locale)
        return date.format(outputFormatter)
    }
}