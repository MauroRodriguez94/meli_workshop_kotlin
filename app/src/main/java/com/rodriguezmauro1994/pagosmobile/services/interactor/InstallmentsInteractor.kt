package com.rodriguezmauro1994.pagosmobile.services.interactor

import com.rodriguezmauro1994.pagosmobile.interfaces.RequestCallback
import com.rodriguezmauro1994.pagosmobile.model.Payment

/**
 * Created by ROD
 */
interface InstallmentsInteractor {
    fun getInstallments(payment: Payment, callback: RequestCallback)
    fun confirmPayment(payment: Payment, callback: RequestCallback)
}