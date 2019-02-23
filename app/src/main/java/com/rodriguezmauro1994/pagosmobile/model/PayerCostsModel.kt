package com.rodriguezmauro1994.pagosmobile.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class PayerCostsModel: Serializable {
    @SerializedName("recommended_message")
    var message: String? = ""
    var installments: Int? = null
}
