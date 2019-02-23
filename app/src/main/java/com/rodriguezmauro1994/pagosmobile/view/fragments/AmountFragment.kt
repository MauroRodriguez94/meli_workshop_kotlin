package com.rodriguezmauro1994.pagosmobile.view.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rodriguezmauro1994.pagosmobile.R
import com.rodriguezmauro1994.pagosmobile.interfaces.KeyboardManagerCallback
import com.rodriguezmauro1994.pagosmobile.interfaces.TextWatcherCallback
import com.rodriguezmauro1994.pagosmobile.model.Payment
import com.rodriguezmauro1994.pagosmobile.presenter.AmountPresenter
import com.rodriguezmauro1994.pagosmobile.presenter.contracts.AmountContract
import com.rodriguezmauro1994.pagosmobile.utils.ButtonUtils
import com.rodriguezmauro1994.pagosmobile.utils.IntKeyboardManager
import com.rodriguezmauro1994.pagosmobile.utils.PointAndCommaUtils
import com.rodriguezmauro1994.pagosmobile.view.activities.MainActivity
import kotlinx.android.synthetic.main.fragment_amount_new.*

/**
 * Created by ROD
 */
class AmountFragment: Fragment(), AmountContract.View {
    private lateinit var presenter: AmountContract.Presenter
    private lateinit var pointAndCommaUtils: PointAndCommaUtils
    private var amount = 0.0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
        = inflater.inflate(R.layout.fragment_amount_new, container, false)!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = AmountPresenter(this)

        handleEvents()
        disableConfirmButton()
    }

    private fun handleEvents() {
        pointAndCommaUtils = PointAndCommaUtils(object: TextWatcherCallback {
            override fun onTextChanged(value: Double, text: String) {
                amount = value
                tvAmount.text = text
                presenter.toggleConfirmButton(amount)
            }
        })

        IntKeyboardManager(tlIntKeyboard, object: KeyboardManagerCallback {
            override fun setUpNumber(number: String): Boolean {
                return pointAndCommaUtils.textChanged(number)
            }
        })

        btConfirm.setOnClickListener { _ ->
            goToPaymentMethods()
        }
    }

    private fun goToPaymentMethods() {
        val payment = Payment()
        payment.amount = amount

        PaymentMethodsView(payment, activity as MainActivity).show()
    }

    override fun enableConfirmButton() {
        ButtonUtils.enableButton(context!!, btConfirm)
    }

    override fun disableConfirmButton() {
        ButtonUtils.disableButton(context!!, btConfirm)
    }
}