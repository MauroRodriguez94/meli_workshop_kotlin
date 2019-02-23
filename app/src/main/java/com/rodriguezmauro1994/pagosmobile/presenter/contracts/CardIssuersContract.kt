package com.rodriguezmauro1994.pagosmobile.presenter.contracts

import com.rodriguezmauro1994.pagosmobile.model.CardIssuerModel
import com.rodriguezmauro1994.pagosmobile.model.ImageTextModel

/**
 * Created by ROD
 */
interface CardIssuersContract {
    interface View: ImageTextRecyclerViewContract

    interface Presenter {
        fun getCardIssuers()
        fun findCardIssuer(imageText: ImageTextModel): CardIssuerModel
    }
}