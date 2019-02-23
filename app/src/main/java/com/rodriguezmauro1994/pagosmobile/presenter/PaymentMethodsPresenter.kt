package com.rodriguezmauro1994.pagosmobile.presenter

import android.graphics.Bitmap
import com.rodriguezmauro1994.pagosmobile.R
import com.rodriguezmauro1994.pagosmobile.interfaces.RequestCallback
import com.rodriguezmauro1994.pagosmobile.model.ImageTextModel
import com.rodriguezmauro1994.pagosmobile.model.PaymentMethodModel
import com.rodriguezmauro1994.pagosmobile.presenter.contracts.ImageTextRecyclerViewContract
import com.rodriguezmauro1994.pagosmobile.presenter.contracts.PaymentMethodsContract
import com.rodriguezmauro1994.pagosmobile.services.interactor.PaymentMethodInteractor

/**
 * Created by ROD
 */
class PaymentMethodsPresenter(private var view: ImageTextRecyclerViewContract,
                              private var interactor: PaymentMethodInteractor): PaymentMethodsContract.Presenter {
    lateinit var paymentMethods: ArrayList<PaymentMethodModel>
    lateinit var imageTextsList: ArrayList<ImageTextModel>

    @Suppress("UNCHECKED_CAST")
    override fun getPaymentMethods() {
        view.showSkeleton()
        interactor.getPaymentMethods(object: RequestCallback {
            override fun onResponseOk(vararg objs: Any) {
                view.hideSkeleton()
                if(objs.isNotEmpty()) {
                    val paymentMethods = objs[0] as ArrayList<PaymentMethodModel>
                    if (paymentMethods.isNotEmpty()) {
                        organizeLists(paymentMethods)
                        view.loadImageTextAdapter(imageTextsList)
                    } else {
                        view.showEmptyState(R.string.empty_state_placeholder, R.string.empty_state_payment_method)
                    }
                }
            }

            override fun onResponseFailed(vararg objs: Any) {
                view.hideSkeleton()
                view.showErrorEmptyState()
            }
        })
    }

    fun organizeLists(result: ArrayList<PaymentMethodModel>) {
        paymentMethods = ArrayList()
        imageTextsList = ArrayList()

        for(paymentMethod in result) {
            if(paymentMethod.status == "active" && paymentMethod.type == "credit_card") {
                paymentMethods.add(paymentMethod)
                imageTextsList.add(ImageTextModel(paymentMethod.id!!, paymentMethod.name!!, paymentMethod.thumbnailURL!!))
                getThumbnail(imageTextsList.last())
            }
        }
    }

    fun getThumbnail(imageText: ImageTextModel) {
        if(!imageText.thumbnailURL.isEmpty()) {
            interactor.getThumbnail(imageText, object: RequestCallback {
                override fun onResponseOk(vararg objs: Any) {
                    view.showThumbnail(objs[0] as String, objs[1] as Bitmap)
                }
                override fun onResponseFailed(vararg objs: Any) {}
            })
        }
    }

    override fun findPaymentMethod(imageText: ImageTextModel): PaymentMethodModel {
        val paymentMethod = paymentMethods.find { it -> it.id == imageText.id }!!
        paymentMethod.thumbnailImage = imageText.thumbnailImage

        return paymentMethod
    }
}
