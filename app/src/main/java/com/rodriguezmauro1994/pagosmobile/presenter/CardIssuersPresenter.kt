package com.rodriguezmauro1994.pagosmobile.presenter

import android.graphics.Bitmap
import com.rodriguezmauro1994.pagosmobile.R
import com.rodriguezmauro1994.pagosmobile.interfaces.RequestCallback
import com.rodriguezmauro1994.pagosmobile.model.CardIssuerModel
import com.rodriguezmauro1994.pagosmobile.model.ImageTextModel
import com.rodriguezmauro1994.pagosmobile.model.Payment
import com.rodriguezmauro1994.pagosmobile.presenter.contracts.CardIssuersContract
import com.rodriguezmauro1994.pagosmobile.presenter.contracts.ImageTextRecyclerViewContract
import com.rodriguezmauro1994.pagosmobile.services.interactor.CardIssuersInteractor

/**
 * Created by ROD
 */
class CardIssuersPresenter(private val payment: Payment,
                           private val view: ImageTextRecyclerViewContract,
                           private var interactor: CardIssuersInteractor) : CardIssuersContract.Presenter {
    lateinit var cardIssuers: ArrayList<CardIssuerModel>
    lateinit var imageTextsList: ArrayList<ImageTextModel>

    @Suppress("UNCHECKED_CAST")
    override fun getCardIssuers() {
        view.showSkeleton()
        interactor.getCardIssuers(payment.paymentMethod!!.id!!, object: RequestCallback {
            override fun onResponseOk(vararg objs: Any) {
                view.hideSkeleton()
                if(objs.isNotEmpty()) {
                    val cardIssuers = objs[0] as ArrayList<CardIssuerModel>
                    if(cardIssuers.isNotEmpty()){
                        organizeLists(cardIssuers)
                        view.loadImageTextAdapter(imageTextsList)
                    } else {
                        view.showEmptyState(R.string.empty_state_placeholder, R.string.empty_state_bank)
                    }
                }
            }

            override fun onResponseFailed(vararg objs: Any) {
                view.hideSkeleton()
                view.showErrorEmptyState()
            }
        })
    }

    fun organizeLists(result: ArrayList<CardIssuerModel>) {
        cardIssuers = result
        imageTextsList = ArrayList()
        for(cardIssuer in result) {
            imageTextsList.add(ImageTextModel(cardIssuer.id!!, cardIssuer.name!!, cardIssuer.thumbnailURL!!))
            getThumbnail(imageTextsList.last())
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

    override fun findCardIssuer(imageText: ImageTextModel): CardIssuerModel {
        val cardIssuer = cardIssuers.find {  it -> it.id == imageText.id }!!
        cardIssuer.thumbnailImage = imageText.thumbnailImage

        return cardIssuer
    }
}
