package com.rodriguezmauro1994.pagosmobile.view.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.rodriguezmauro1994.pagosmobile.R
import com.rodriguezmauro1994.pagosmobile.model.Payment
import com.rodriguezmauro1994.pagosmobile.utils.Formats
import kotlinx.android.synthetic.main.fragment_summary.*

/**
 * Created by ROD
 */
class SummaryFragment: Fragment() {
    private lateinit var payment: Payment

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            = inflater.inflate(R.layout.fragment_summary, container, false)!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        payment = arguments!!.getSerializable("payment") as Payment

        loadPayment()
    }

    private fun loadPayment() {
        loadAmount()
        loadPaymentMethod()
        loadCardIssuer()
        loadInstallments()
    }

    private fun loadAmount() {
        tvAmount.text = getString(R.string.ars_price, Formats.convertToPrice(payment.amount!!))
    }

    private fun loadPaymentMethod() {
        llPaymentMethod.findViewById<ImageView>(R.id.ivImage).setImageBitmap(payment.paymentMethod!!.thumbnailImage)
        llPaymentMethod.findViewById<TextView>(R.id.tvText).text = payment.paymentMethod!!.name
    }

    private fun loadCardIssuer() {
        llCardIssuer.findViewById<ImageView>(R.id.ivImage).setImageBitmap(payment.cardIssuer!!.thumbnailImage)
        llCardIssuer.findViewById<TextView>(R.id.tvText).text = payment.cardIssuer!!.name
    }

    private fun loadInstallments() {
        llInstallments.findViewById<TextView>(R.id.tvTitle).text = payment.payerCost!!.installments.toString()
        llInstallments.findViewById<TextView>(R.id.tvSubtitle).text = payment.payerCost!!.message
    }
}