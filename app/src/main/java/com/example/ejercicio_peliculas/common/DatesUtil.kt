package com.example.ejercicio_peliculas.common

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DatesUtil {

    fun simpleDateConverter(date: String): Date? {
        val originalFormant = SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH)
        return try {
            originalFormant.parse(date)
        } catch (e: Exception) {
            originalFormant.parse("01 01 2000")
        }
    }

}