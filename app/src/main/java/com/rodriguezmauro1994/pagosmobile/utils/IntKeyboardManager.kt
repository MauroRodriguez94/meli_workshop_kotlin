package com.rodriguezmauro1994.pagosmobile.utils

import android.widget.ImageView
import android.widget.TableLayout
import android.widget.TextView
import com.rodriguezmauro1994.pagosmobile.R
import com.rodriguezmauro1994.pagosmobile.interfaces.KeyboardManagerCallback

/**
 * Created by ROD
 */
class IntKeyboardManager(keyboard: TableLayout, private val callback: KeyboardManagerCallback) {
    private var tvOne: TextView = keyboard.findViewById(R.id.tvOne)
    private var tvTwo: TextView = keyboard.findViewById(R.id.tvTwo)
    private var tvThree: TextView = keyboard.findViewById(R.id.tvThree)
    private var tvFour: TextView = keyboard.findViewById(R.id.tvFour)
    private var tvFive: TextView = keyboard.findViewById(R.id.tvFive)
    private var tvSix: TextView = keyboard.findViewById(R.id.tvSix)
    private var tvSeven: TextView = keyboard.findViewById(R.id.tvSeven)
    private var tvEight: TextView = keyboard.findViewById(R.id.tvEight)
    private var tvNine: TextView = keyboard.findViewById(R.id.tvNine)
    private var tvZero: TextView = keyboard.findViewById(R.id.tvZero)
    private var ivBack: ImageView = keyboard.findViewById(R.id.ivBack)
    private var number = "0"
    private var buffer = "0"

    init {
        manageOnClicks()
    }

    private fun manageOnClicks() {
        tvOne.setOnClickListener { add("1") }
        tvTwo.setOnClickListener { add("2") }
        tvThree.setOnClickListener { add("3") }
        tvFour.setOnClickListener { add("4") }
        tvFive.setOnClickListener { add("5") }
        tvSix.setOnClickListener { add("6") }
        tvSeven.setOnClickListener { add("7") }
        tvEight.setOnClickListener { add("8") }
        tvNine.setOnClickListener { add("9") }
        tvZero.setOnClickListener { add("0") }
        ivBack.setOnClickListener { back() }
    }

    private fun add(numberToAdd: String) {
        buffer += numberToAdd
        if (callback.setUpNumber(buffer)) {
            this.number = buffer
        } else {
            buffer = number
        }
    }

    private fun back() {
        if (number.length == 1) {
            number = "0"
            buffer = "0"
        } else {
            number = number.substring(0, number.length - 1)
            buffer = number
        }

        callback.setUpNumber(number)
    }
}
