package com.rodriguezmauro1994.pagosmobile.services.interactor

import com.rodriguezmauro1994.pagosmobile.interfaces.RequestCallback
import com.rodriguezmauro1994.pagosmobile.model.ImageTextModel

/**
 * Created by ROD
 */
interface CardIssuersInteractor {
    fun getCardIssuers(paymentId: String, callback: RequestCallback)
    fun getThumbnail(imageText: ImageTextModel, callback: RequestCallback)
}