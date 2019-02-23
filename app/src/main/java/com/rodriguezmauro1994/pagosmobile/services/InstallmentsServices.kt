package com.rodriguezmauro1994.pagosmobile.services

import android.content.Context
import android.os.Handler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rodriguezmauro1994.pagosmobile.interfaces.RequestCallback
import com.rodriguezmauro1994.pagosmobile.model.*
import com.rodriguezmauro1994.pagosmobile.services.interactor.InstallmentsInteractor
import com.rodriguezmauro1994.pagosmobile.services.wrappers.Services
import com.rodriguezmauro1994.pagosmobile.services.wrappers.VolleyWrapper

/**
 * Created by ROD
 */
class InstallmentsServices(private val context: Context): Services(), InstallmentsInteractor {
    override fun getInstallments(payment: Payment, callback: RequestCallback) {
        val params = ArrayList<ParamsModel>()
        params.add(ParamsModel("amount", payment.amount!!))
        params.add(ParamsModel("payment_method_id", payment.paymentMethod!!.id!!))
        params.add(ParamsModel("issuer.id", payment.cardIssuer!!.id!!))
        val url = generateURL(Methods.PAYMENT,"installments", params)

        sendRequest(url, VolleyWrapper.getInstance(context, object: RequestCallback {
            override fun onResponseOk(vararg objs: Any) {
                try {
                    val response = objs[0] as String

                    val listType = object : TypeToken<List<InstallmentsResponseModel>>() {}.type
                    val installments: ArrayList<InstallmentsResponseModel> = Gson().fromJson(response, listType)

                    if(installments.isNotEmpty()) {
                        callback.onResponseOk(installments[0].payerCosts!!)
                    } else {
                        callback.onResponseFailed()
                    }
                } catch (e: Exception) {
                    callback.onResponseFailed()
                }
            }

            override fun onResponseFailed(vararg objs: Any) {
                callback.onResponseFailed()
            }
        }))
    }

    override fun confirmPayment(payment: Payment, callback: RequestCallback) {
        //FIXME cambiar por servicio de verdad si se quiere confirmar
        val handler = Handler()
        val runnable = object : Runnable {
            override fun run() {
                callback.onResponseOk()
            }
        }

        handler.postDelayed(runnable, 1000)
    }
}
