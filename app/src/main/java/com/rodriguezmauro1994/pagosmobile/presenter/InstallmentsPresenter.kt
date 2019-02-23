package com.rodriguezmauro1994.pagosmobile.presenter

import com.rodriguezmauro1994.pagosmobile.R
import com.rodriguezmauro1994.pagosmobile.interfaces.RequestCallback
import com.rodriguezmauro1994.pagosmobile.model.PayerCostsModel
import com.rodriguezmauro1994.pagosmobile.model.Payment
import com.rodriguezmauro1994.pagosmobile.model.TitleSubtitleModel
import com.rodriguezmauro1994.pagosmobile.presenter.contracts.InstallmentsContract
import com.rodriguezmauro1994.pagosmobile.services.interactor.InstallmentsInteractor

/**
 * Created by ROD
 */
class InstallmentsPresenter(private var payment: Payment,
                            private val view: InstallmentsContract.View,
                            private val interactor: InstallmentsInteractor) : InstallmentsContract.Presenter {
    lateinit var payerCosts: ArrayList<PayerCostsModel>
    lateinit var titleSubtitleList: ArrayList<TitleSubtitleModel>
    private var transactionComplete = false

    @Suppress("UNCHECKED_CAST")
    override fun getInstallments() {
        view.showSkeleton()
        interactor.getInstallments(payment, object: RequestCallback {
            override fun onResponseOk(vararg objs: Any) {
                view.hideSkeleton()
                if(objs.isNotEmpty()) {
                    val payerCosts = objs[0] as ArrayList<PayerCostsModel>
                    if(payerCosts.isNotEmpty()) {
                        organizeLists(payerCosts)
                        view.loadTitleSubtitleAdapter(titleSubtitleList)
                    } else {
                        view.showEmptyState(R.string.empty_state_placeholder, R.string.empty_state_installments)
                    }
                }
            }

            override fun onResponseFailed(vararg objs: Any) {
                view.hideSkeleton()
                view.showErrorEmptyState()
            }
        })
    }

    fun organizeLists(payerCosts: ArrayList<PayerCostsModel>) {
        this.payerCosts = payerCosts
        this.titleSubtitleList = ArrayList()

        for(payerCost in payerCosts) {
            val installments = payerCost.installments!!.toString()
            titleSubtitleList.add(TitleSubtitleModel(installments, installments, payerCost.message!!))
        }
    }

    override fun findPayerCost(titleSubtitle: TitleSubtitleModel): PayerCostsModel {
        return payerCosts.find { it -> it.installments.toString() == titleSubtitle.id }!!
    }

    override fun confirmPayment(payment: Payment) {
        this.payment = payment
        interactor.confirmPayment(payment, object: RequestCallback {
            override fun onResponseOk(vararg objs: Any) {
                transactionComplete = true
                view.onTransactionOk()
            }

            override fun onResponseFailed(vararg objs: Any) {
                view.onTransactionFailed()
            }
        })
    }

    override fun onActionClicked() {
        if(transactionComplete) {
            view.backToHome()
        } else {
            confirmPayment(payment)
        }
    }

    override fun setTransactionComplete(isComplete: Boolean) {
        this.transactionComplete = isComplete
    }
}
