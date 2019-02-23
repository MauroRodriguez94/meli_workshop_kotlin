package com.rodriguezmauro1994.pagosmobile.presenter

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.rodriguezmauro1994.pagosmobile.model.*
import com.rodriguezmauro1994.pagosmobile.presenter.contracts.InstallmentsContract
import com.rodriguezmauro1994.pagosmobile.services.interactor.InstallmentsInteractor
import junit.framework.Assert
import junit.framework.Assert.assertEquals
import org.junit.Test

import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by ROD
 */
@RunWith(MockitoJUnitRunner::class)
class InstallmentsPresenterTest {
    @Mock
    private lateinit var view: InstallmentsContract.View
    @Mock
    private lateinit var interactor: InstallmentsInteractor
    private lateinit var presenter: InstallmentsPresenter
    private lateinit var payment: Payment
    private var payerCosts = generatePayerCosts()

    @Before
    fun setUp() {
        payment = Payment()
        payment.paymentMethod = PaymentMethodModel()
        payment.paymentMethod!!.id = "paymentMethod"
        payment.cardIssuer = CardIssuerModel()
        payment.cardIssuer!!.id = "cardIssuer"

        presenter = InstallmentsPresenter(payment, view, interactor)
    }

    @Test
    fun getInstallments() {
        presenter.getInstallments()
        verify(view, times(1)).showSkeleton()
        verify(interactor, times(1)).getInstallments(eq(payment), any())
    }

    @Test
    fun findPayerCost() {
        presenter.organizeLists(payerCosts)
        assertEquals(presenter.findPayerCost(TitleSubtitleModel("1", "1", "x1")), payerCosts[0])
    }

    @Test
    fun confirmPayment() {
        presenter.confirmPayment(payment)
        verify(interactor, times(1)).confirmPayment(eq(payment), any())
    }

    @Test
    fun onActionClickedTrue() {
        presenter.setTransactionComplete(true)
        presenter.onActionClicked()
        verify(view, times(1)).backToHome()
    }

    @Test
    fun onActionClickedFalse() {
        presenter.setTransactionComplete(false)
        presenter.onActionClicked()
        verify(interactor, times(1)).confirmPayment(eq(payment), any())
    }

    @Test
    fun organizeLists() {
        presenter.organizeLists(payerCosts)
        Assert.assertEquals(3, presenter.payerCosts.size)
        Assert.assertEquals(3, presenter.titleSubtitleList.size)
    }

    private fun generatePayerCosts(): ArrayList<PayerCostsModel> {
        val payerCosts = ArrayList<PayerCostsModel>()

        val oneDue = PayerCostsModel()
        oneDue.installments = 1
        oneDue.message = "x1"
        payerCosts.add(oneDue)

        val twoDues = PayerCostsModel()
        twoDues.installments = 2
        twoDues.message = "x2"
        payerCosts.add(twoDues)

        val threeDues = PayerCostsModel()
        threeDues.installments = 3
        threeDues.message = "x3"
        payerCosts.add(threeDues)

        return payerCosts
    }
}