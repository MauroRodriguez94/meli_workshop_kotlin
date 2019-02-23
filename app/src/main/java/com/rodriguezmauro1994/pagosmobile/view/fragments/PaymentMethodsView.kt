package com.rodriguezmauro1994.pagosmobile.view.fragments

import com.rodriguezmauro1994.pagosmobile.R
import com.rodriguezmauro1994.pagosmobile.interfaces.OnItemClickCallback
import com.rodriguezmauro1994.pagosmobile.interfaces.SimpleCallback
import com.rodriguezmauro1994.pagosmobile.model.ImageTextModel
import com.rodriguezmauro1994.pagosmobile.model.Payment
import com.rodriguezmauro1994.pagosmobile.presenter.PaymentMethodsPresenter
import com.rodriguezmauro1994.pagosmobile.presenter.contracts.PaymentMethodsContract
import com.rodriguezmauro1994.pagosmobile.services.PaymentMethodsServices
import com.rodriguezmauro1994.pagosmobile.view.activities.MainActivity

/**
 * Created by ROD
 */
class PaymentMethodsView (private var payment: Payment, private var activity: MainActivity) {
    private lateinit var fragment: GenericRecyclerViewFragment
    private lateinit var presenter: PaymentMethodsContract.Presenter

    fun show() {
        fragment = GenericRecyclerViewFragment.getInstance(R.string.payment_method_title, R.drawable.ic_credit_card_holder,
                object : OnItemClickCallback {
                    override fun onClick(imageText: ImageTextModel) {
                        payment.paymentMethod = presenter.findPaymentMethod(imageText)

                        CardIssuersView(payment, activity).show()
                    }
                },
                object : SimpleCallback {
                    override fun onCallback() {
                        presenter.getPaymentMethods()
                    }
                })

        activity.goToFragment(fragment)
        presenter = PaymentMethodsPresenter(fragment, PaymentMethodsServices(activity))
    }
}
