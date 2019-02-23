package com.rodriguezmauro1994.pagosmobile.utils

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

/**
 * Created by ROD
 */
class Formats {
    companion object {
        fun convertToPrice(number: Double): String {
            var finalNumber = number
            var sign = ""

            if (number < 0) {
                sign = "-"
                finalNumber *= -1
            }

            if (number > -1 && number < 1) {
                sign += "0"
            }

            val symbols = DecimalFormatSymbols(Locale.GERMANY)
            symbols.decimalSeparator = ','
            symbols.groupingSeparator = '.'
            val myFormatter = DecimalFormat("###,###.00", symbols)

            return sign + myFormatter.format(finalNumber)
        }
    }
}