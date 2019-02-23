package com.rodriguezmauro1994.pagosmobile.utils

import com.rodriguezmauro1994.pagosmobile.interfaces.TextWatcherCallback
import java.text.NumberFormat
import java.util.*
import java.util.regex.Pattern

/**
 * Created by ROD
 */
class PointAndCommaUtils(private var callback: TextWatcherCallback?) {
    private var buffer = "000"
    private var current = String()
    private val REGEX = "[0-9]{0,10}"

    fun textChanged(number: String): Boolean {
        var textChanged = true
        if(number != current) {
            var cleanString = cleanString(number)

            if(matchesWithRegex(cleanString)) {
                buffer = cleanString
            }else {
                textChanged = false
                cleanString = buffer
            }

            val tempDouble = parseStringCleaned(cleanString)

            callback?.onTextChanged(tempDouble, current)
        }

        return textChanged
    }

    private fun cleanString(string: String): String {
        var stringCleaned = string.replace(Regex("[,.e]"), "")
        if(stringCleaned.length < 3) {
            stringCleaned = addZerosToString(stringCleaned)
        }

        return stringCleaned
    }

    private fun addZerosToString(stringCleaned: String): String {
        var stringCleaned1 = stringCleaned
        for (zero in 0..3 - stringCleaned1.length) {
            stringCleaned1 = "0" + stringCleaned1
        }
        return stringCleaned1
    }

    private fun parseStringCleaned(cleanString: String): Double {
        val tempString = cleanString.substring(0, cleanString.length - 2) + "." + cleanString.substring(cleanString.length - 2, cleanString.length)
        val tempDouble = tempString.toDouble()
        current = formatParsedDouble(tempDouble)
        return tempDouble
    }

    private fun formatParsedDouble(parsedDouble: Double): String {
        val numberFormat = NumberFormat.getNumberInstance(Locale.GERMANY)
        numberFormat.minimumFractionDigits = 2
        numberFormat.maximumFractionDigits = 2

        return numberFormat.format(parsedDouble)
    }

    private fun matchesWithRegex(string: String)
            = Pattern.matches(REGEX, string)
}
