package com.rodriguezmauro1994.pagosmobile.presenter

import com.rodriguezmauro1994.pagosmobile.presenter.contracts.AmountContract

/**
 * Created by ROD
 */
class AmountPresenter(private val view: AmountContract.View): AmountContract.Presenter {
    override fun toggleConfirmButton(amount: Double) {
        if(amount > 0){
            view.enableConfirmButton()
        } else {
            view.disableConfirmButton()
        }
    }
}