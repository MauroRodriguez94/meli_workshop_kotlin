package com.rodriguezmauro1994.pagosmobile.presenter.contracts

import com.rodriguezmauro1994.pagosmobile.interfaces.RequestCallback
import com.rodriguezmauro1994.pagosmobile.model.PayerCostsModel
import com.rodriguezmauro1994.pagosmobile.model.Payment
import com.rodriguezmauro1994.pagosmobile.model.TitleSubtitleModel

/**
 * Created by ROD
 */
interface InstallmentsContract {
    interface View: TitleSubtitleRecyclerViewContract {
        fun backToHome()
        fun onTransactionOk()
        fun onTransactionFailed()
    }

    interface Presenter {
        fun getInstallments()
        fun findPayerCost(titleSubtitle: TitleSubtitleModel): PayerCostsModel
        fun confirmPayment(payment: Payment)
        fun onActionClicked()
        fun setTransactionComplete(isComplete: Boolean)
    }
}