package com.rodriguezmauro1994.pagosmobile.presenter.contracts

import com.rodriguezmauro1994.pagosmobile.model.ImageTextModel
import com.rodriguezmauro1994.pagosmobile.model.PaymentMethodModel

/**
 * Created by ROD
 */
interface PaymentMethodsContract {
    interface View: ImageTextRecyclerViewContract

    interface Presenter {
        fun getPaymentMethods()
        fun findPaymentMethod(imageText: ImageTextModel): PaymentMethodModel
    }
}