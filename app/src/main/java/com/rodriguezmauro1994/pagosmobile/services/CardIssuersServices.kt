package com.rodriguezmauro1994.pagosmobile.services

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rodriguezmauro1994.pagosmobile.interfaces.RequestCallback
import com.rodriguezmauro1994.pagosmobile.model.CardIssuerModel
import com.rodriguezmauro1994.pagosmobile.model.ImageTextModel
import com.rodriguezmauro1994.pagosmobile.model.Methods
import com.rodriguezmauro1994.pagosmobile.model.ParamsModel
import com.rodriguezmauro1994.pagosmobile.services.interactor.CardIssuersInteractor
import com.rodriguezmauro1994.pagosmobile.services.wrappers.Services
import com.rodriguezmauro1994.pagosmobile.services.wrappers.VolleyWrapper

/**
 * Created by ROD
 */
class CardIssuersServices(private val context: Context): Services(), CardIssuersInteractor {
    override fun getCardIssuers(paymentId: String, callback: RequestCallback) {
        val params = ArrayList<ParamsModel>()
        params.add(ParamsModel("payment_method_id", paymentId))
        val url = generateURL(Methods.PAYMENT,"card_issuers", params)

        sendRequest(url, VolleyWrapper.getInstance(context, object: RequestCallback {
            override fun onResponseOk(vararg objs: Any) {
                try {
                    val response = objs[0] as String

                    val listType = object : TypeToken<List<CardIssuerModel>>() {}.type
                    val cardIssuers: ArrayList<CardIssuerModel> = Gson().fromJson(response, listType)

                    callback.onResponseOk(cardIssuers)
                } catch (e: Exception) {
                    callback.onResponseFailed()
                }
            }

            override fun onResponseFailed(vararg objs: Any) {
                callback.onResponseFailed()
            }
        }))
    }

    override fun getThumbnail(imageText: ImageTextModel, callback: RequestCallback) {
        ImageServices(context).getThumbnail(imageText, callback)
    }
}