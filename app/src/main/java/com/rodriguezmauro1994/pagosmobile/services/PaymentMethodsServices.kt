package com.rodriguezmauro1994.pagosmobile.services

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rodriguezmauro1994.pagosmobile.interfaces.RequestCallback
import com.rodriguezmauro1994.pagosmobile.model.ImageTextModel
import com.rodriguezmauro1994.pagosmobile.model.Methods
import com.rodriguezmauro1994.pagosmobile.model.PaymentMethodModel
import com.rodriguezmauro1994.pagosmobile.services.interactor.PaymentMethodInteractor
import com.rodriguezmauro1994.pagosmobile.services.wrappers.Services
import com.rodriguezmauro1994.pagosmobile.services.wrappers.VolleyWrapper

/**
 * Created by ROD
 */
class PaymentMethodsServices(private var context: Context): Services(), PaymentMethodInteractor {

    override fun getPaymentMethods(callback: RequestCallback) {
        val url = generateURL(Methods.PAYMENT,"")
        sendRequest(url, VolleyWrapper.getInstance(context, object: RequestCallback {
            override fun onResponseOk(vararg objs: Any) {
                try {
                    val response = objs[0] as String

                    val listType = object : TypeToken<List<PaymentMethodModel>>() {}.type
                    val paymentMethods: ArrayList<PaymentMethodModel> = Gson().fromJson(response, listType)

                    callback.onResponseOk(paymentMethods)
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