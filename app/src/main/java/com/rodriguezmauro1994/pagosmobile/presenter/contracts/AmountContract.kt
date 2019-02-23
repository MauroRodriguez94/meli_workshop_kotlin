package com.rodriguezmauro1994.pagosmobile.presenter.contracts

/**
 * Created by ROD
 */
interface AmountContract {
    interface View {
        fun enableConfirmButton()
        fun disableConfirmButton()
    }
    interface Presenter {
        fun toggleConfirmButton(amount: Double)
    }
}