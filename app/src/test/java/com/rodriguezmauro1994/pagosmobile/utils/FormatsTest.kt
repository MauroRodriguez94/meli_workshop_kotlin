package com.rodriguezmauro1994.pagosmobile.utils

import org.junit.Test

import org.junit.Assert.*

/**
 * Created by ROD
 */
class FormatsTest {
    @Test
    fun convertToPrice() {
        assertEquals("-1.000,50", Formats.convertToPrice(-1000.5))
        assertEquals("-100,00", Formats.convertToPrice(-100.0))
        assertEquals("-1,00", Formats.convertToPrice(-1.0))
        assertEquals("-0,50", Formats.convertToPrice(-0.5))
        assertEquals("0,00", Formats.convertToPrice(0.0))
        assertEquals("0,50", Formats.convertToPrice(0.50))
        assertEquals("0,53", Formats.convertToPrice(0.53))
        assertEquals("1,00", Formats.convertToPrice(1.0))
        assertEquals("1,53", Formats.convertToPrice(1.53))
        assertEquals("10,00", Formats.convertToPrice(10.0))
        assertEquals("1.000,00", Formats.convertToPrice(1000.0))
        assertEquals("1.000,50", Formats.convertToPrice(1000.5))
    }
}
