package com.rodriguezmauro1994.pagosmobile.utils

import android.content.Context
import android.support.v4.content.ContextCompat
import android.widget.Button
import com.rodriguezmauro1994.pagosmobile.R

/**
 * Created by ROD
 */
class ButtonUtils {
    companion object {
        fun enableButton(context: Context, button: Button) {
            button.isEnabled = true
            button.setBackgroundColor(ContextCompat.getColor(context, R.color.buttonEnabled))
        }

        fun disableButton(context: Context, button: Button) {
            button.isEnabled = false
            button.setBackgroundColor(ContextCompat.getColor(context, R.color.buttonDisabled))
        }
    }
}