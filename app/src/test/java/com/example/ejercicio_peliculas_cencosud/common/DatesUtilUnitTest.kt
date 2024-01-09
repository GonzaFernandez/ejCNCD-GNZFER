package com.example.ejercicio_peliculas_cencosud.common

import com.example.ejercicio_peliculas_cencosud.common.DatesUtil.simpleDateConverter
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.Date

@RunWith(JUnit4::class)
class DatesUtilUnitTest {
    @Test
    fun simpleDateConverter_test(){
        val dateStr = "20 Jan 2023"
        val result: Date? = simpleDateConverter(dateStr)
        assertTrue(result != null)
        assertTrue(result is Date)
    }
}