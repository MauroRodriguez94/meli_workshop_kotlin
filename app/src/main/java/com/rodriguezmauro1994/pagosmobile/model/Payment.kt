package com.rodriguezmauro1994.pagosmobile.model

import java.io.Serializable

/**
 * Created by ROD
 */
class Payment: Serializable {
    var amount: Double? = 0.0
    var paymentMethod: PaymentMethodModel? = null
    var cardIssuer: CardIssuerModel? = null
    var payerCost: PayerCostsModel? = null
}