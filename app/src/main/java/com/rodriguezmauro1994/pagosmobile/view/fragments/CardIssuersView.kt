package com.rodriguezmauro1994.pagosmobile.view.fragments

import com.rodriguezmauro1994.pagosmobile.R
import com.rodriguezmauro1994.pagosmobile.interfaces.OnItemClickCallback
import com.rodriguezmauro1994.pagosmobile.interfaces.SimpleCallback
import com.rodriguezmauro1994.pagosmobile.model.ImageTextModel
import com.rodriguezmauro1994.pagosmobile.model.Payment
import com.rodriguezmauro1994.pagosmobile.presenter.CardIssuersPresenter
import com.rodriguezmauro1994.pagosmobile.presenter.contracts.CardIssuersContract
import com.rodriguezmauro1994.pagosmobile.services.CardIssuersServices
import com.rodriguezmauro1994.pagosmobile.view.activities.MainActivity

/**
 * Created by ROD
 */
class CardIssuersView (private var payment: Payment, private var activity: MainActivity) {
    private lateinit var fragment: GenericRecyclerViewFragment
    private lateinit var presenter: CardIssuersContract.Presenter

    fun show() {
        fragment = GenericRecyclerViewFragment.getInstance(R.string.card_issuers_title, R.drawable.ic_bank_holder,
                object : OnItemClickCallback {
                    override fun onClick(imageText: ImageTextModel) {
                        payment.cardIssuer = presenter.findCardIssuer(imageText)

                        InstallmentsView(payment, activity).show()
                    }
                },
                object : SimpleCallback {
                    override fun onCallback() {
                        presenter.getCardIssuers()
                    }
                })

        activity.goToFragment(fragment)
        presenter = CardIssuersPresenter(payment, fragment, CardIssuersServices(activity))
    }
}
