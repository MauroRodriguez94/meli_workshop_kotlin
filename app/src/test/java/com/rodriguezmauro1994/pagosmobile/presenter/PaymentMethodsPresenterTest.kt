package com.rodriguezmauro1994.pagosmobile.presenter

import com.nhaarman.mockito_kotlin.*
import com.rodriguezmauro1994.pagosmobile.model.ImageTextModel
import com.rodriguezmauro1994.pagosmobile.model.PaymentMethodModel
import com.rodriguezmauro1994.pagosmobile.presenter.contracts.PaymentMethodsContract
import com.rodriguezmauro1994.pagosmobile.services.interactor.PaymentMethodInteractor
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by ROD
 */
@RunWith(MockitoJUnitRunner::class)
class PaymentMethodsPresenterTest {
    @Mock
    private lateinit var view: PaymentMethodsContract.View
    @Mock
    private lateinit var interactor: PaymentMethodInteractor
    private lateinit var presenter: PaymentMethodsPresenter
    private var paymentMethods = generatePaymentMethods()

    @Before
    fun setUp() {
        presenter = PaymentMethodsPresenter(view, interactor)
    }

    @Test
    fun getPaymentMethods() {
        presenter.getPaymentMethods()
        verify(view, times(1)).showSkeleton()
        verify(interactor, times(1)).getPaymentMethods(any())
    }

    @Test
    fun getThumbnailEmptyURL() {
        presenter.getThumbnail(ImageTextModel("test", "test", ""))
        verify(interactor, never()).getThumbnail(any(), any())
    }

    @Test
    fun getThumbnail() {
        val imageText = ImageTextModel("test", "test", "http://test.png")
        presenter.getThumbnail(imageText)
        verify(interactor, times(1)).getThumbnail(eq(imageText), any())
    }

    @Test
    fun findPaymentMethod() {
        presenter.organizeLists(paymentMethods)
        assertEquals(presenter.findPaymentMethod(ImageTextModel("activeCreditCard", "activeCreditCard", "someUrl")), paymentMethods[0])
    }

    @Test
    fun organizeLists() {
        presenter.organizeLists(paymentMethods)
        assertEquals(2, presenter.paymentMethods.size)
        assertEquals(2, presenter.imageTextsList.size)
        verify(interactor, times(1)).getThumbnail(any(), any())
    }

    private fun generatePaymentMethods(): ArrayList<PaymentMethodModel> {
        val paymentMethods = ArrayList<PaymentMethodModel>()

        val activeCreditCard = PaymentMethodModel()
        activeCreditCard.type = "credit_card"
        activeCreditCard.status = "active"
        activeCreditCard.thumbnailURL = "someUrl"
        activeCreditCard.id = "activeCreditCard"
        activeCreditCard.name = "activeCreditCard"
        paymentMethods.add(activeCreditCard)

        val inactiveCreditCard = PaymentMethodModel()
        inactiveCreditCard.type = "credit_card"
        inactiveCreditCard.status = "inactive"
        inactiveCreditCard.id = "inactiveCreditCard"
        inactiveCreditCard.name = "inactiveCreditCard"
        paymentMethods.add(inactiveCreditCard)

        val activeTicket = PaymentMethodModel()
        activeTicket.type = "ticket"
        activeTicket.status = "active"
        activeTicket.id = "activeTicket"
        activeTicket.name = "activeTicket"
        paymentMethods.add(activeTicket)

        val inactiveTicket = PaymentMethodModel()
        inactiveTicket.type = "ticket"
        inactiveTicket.status = "inactive"
        inactiveTicket.id = "inactiveTicket"
        inactiveTicket.name = "inactiveTicket"
        paymentMethods.add(inactiveTicket)

        val activeCreditCardNoThumbnail = PaymentMethodModel()
        activeCreditCardNoThumbnail.type = "credit_card"
        activeCreditCardNoThumbnail.status = "active"
        activeCreditCardNoThumbnail.id = "activeCreditCardNoThumbnail"
        activeCreditCardNoThumbnail.name = "activeCreditCardNoThumbnail"
        paymentMethods.add(activeCreditCardNoThumbnail)

        val inactiveCreditCardNoThumbnailTwo = PaymentMethodModel()
        inactiveCreditCardNoThumbnailTwo.type = "credit_card"
        inactiveCreditCardNoThumbnailTwo.status = "inactive"
        inactiveCreditCardNoThumbnailTwo.thumbnailURL = "someUrl"
        inactiveCreditCardNoThumbnailTwo.id = "inactiveCreditCardNoThumbnailTwo"
        inactiveCreditCardNoThumbnailTwo.name = "inactiveCreditCardNoThumbnailTwo"
        paymentMethods.add(inactiveCreditCardNoThumbnailTwo)

        return paymentMethods
    }
}
