package com.rodriguezmauro1994.pagosmobile.utils

import com.rodriguezmauro1994.pagosmobile.model.Methods

/**
 * Created by ROD
 */
class Constants {
    companion object {
        val URL = "https://api.mercadopago.com/v1/"
        val METHODS: HashMap<Methods, String> = hashMapOf(Methods.PAYMENT to "payment_methods")
        val API_KEY = "444a9ef5-8a6b-429f-abdf-587639155d88"
        val API_KEY_NAME = "public_key"
    }
}