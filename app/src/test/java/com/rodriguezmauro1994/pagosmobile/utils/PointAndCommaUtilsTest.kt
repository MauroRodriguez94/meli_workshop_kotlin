package com.rodriguezmauro1994.pagosmobile.utils

import com.rodriguezmauro1994.pagosmobile.interfaces.TextWatcherCallback
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by ROD
 */
@RunWith(MockitoJUnitRunner::class)
class PointAndCommaUtilsTest {
    private val delta = 1e-15

    @Test
    fun textChangedZero() {
        val instance = PointAndCommaUtils(object: TextWatcherCallback {
            override fun onTextChanged(value: Double, text: String) {
                assertEquals(0.0, value, delta)
                assertEquals("0,00", text)
            }
        })
        instance.textChanged("0")
    }

    @Test
    fun textChangedZeroPointZeroFive() {
        val instance = PointAndCommaUtils(object: TextWatcherCallback {
            override fun onTextChanged(value: Double, text: String) {
                assertEquals(0.05, value, delta)
                assertEquals("0,05", text)
            }
        })
        instance.textChanged("05")
    }

    @Test
    fun textChangedZeroPointFive() {
        val instance = PointAndCommaUtils(object: TextWatcherCallback {
            override fun onTextChanged(value: Double, text: String) {
                assertEquals(0.5, value, delta)
                assertEquals("0,50", text)
            }
        })
        instance.textChanged("050")
    }

    @Test
    fun textChangedFive() {
        val instance = PointAndCommaUtils(object: TextWatcherCallback {
            override fun onTextChanged(value: Double, text: String) {
                assertEquals(5.0, value, delta)
                assertEquals("5,00", text)
            }
        })
        instance.textChanged("0500")
    }

    @Test
    fun textChangedFiveHundred() {
        val instance = PointAndCommaUtils(object: TextWatcherCallback {
            override fun onTextChanged(value: Double, text: String) {
                assertEquals(500.0, value, delta)
                assertEquals("500,00", text)
            }
        })
        instance.textChanged("050000")
    }

    @Test
    fun textChangedFiveThousand() {
        val instance = PointAndCommaUtils(object: TextWatcherCallback {
            override fun onTextChanged(value: Double, text: String) {
                assertEquals(5000.0, value, delta)
                assertEquals("5.000,00", text)
            }
        })
        instance.textChanged("0500000")
    }

    @Test
    fun textChangedFiveHundredThousand() {
        val instance = PointAndCommaUtils(object: TextWatcherCallback {
            override fun onTextChanged(value: Double, text: String) {
                assertEquals(500000.0, value, delta)
                assertEquals("500.000,00", text)
            }
        })
        instance.textChanged("050000000")
    }

    @Test
    fun textChangedFiveMillion() {
        val instance = PointAndCommaUtils(object: TextWatcherCallback {
            override fun onTextChanged(value: Double, text: String) {
                assertEquals(5000000.0, value, delta)
                assertEquals("5.000.000,00", text)
            }
        })
        instance.textChanged("0500000000")
    }

    @Test
    fun textChangedExtraLarge() {
        val instance = PointAndCommaUtils(object: TextWatcherCallback {
            override fun onTextChanged(value: Double, text: String) {
                assertEquals(0.0, value, delta)
                assertEquals("0,00", text)
            }
        })
        instance.textChanged("05000000000")
    }
}