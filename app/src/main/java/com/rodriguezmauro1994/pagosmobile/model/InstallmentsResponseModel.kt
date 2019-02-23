package com.rodriguezmauro1994.pagosmobile.model

import com.google.gson.annotations.SerializedName

/**
 * Created by ROD
 */
class InstallmentsResponseModel {
    @SerializedName("payer_costs")
    var payerCosts: ArrayList<PayerCostsModel>? = null
}