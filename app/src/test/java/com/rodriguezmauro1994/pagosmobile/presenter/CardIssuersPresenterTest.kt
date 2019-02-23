package com.rodriguezmauro1994.pagosmobile.presenter

import com.nhaarman.mockito_kotlin.*
import com.rodriguezmauro1994.pagosmobile.model.CardIssuerModel
import com.rodriguezmauro1994.pagosmobile.model.ImageTextModel
import com.rodriguezmauro1994.pagosmobile.model.Payment
import com.rodriguezmauro1994.pagosmobile.model.PaymentMethodModel
import com.rodriguezmauro1994.pagosmobile.presenter.contracts.CardIssuersContract
import com.rodriguezmauro1994.pagosmobile.services.interactor.CardIssuersInteractor
import junit.framework.Assert
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by ROD
 */
@RunWith(MockitoJUnitRunner::class)
class CardIssuersPresenterTest {
    @Mock
    private lateinit var view: CardIssuersContract.View
    @Mock
    private lateinit var interactor: CardIssuersInteractor
    private lateinit var presenter: CardIssuersPresenter
    private lateinit var payment: Payment
    private var cardIssuers = generateCardIssuers()

    @Before
    fun setUp() {
        payment = Payment()
        payment.paymentMethod = PaymentMethodModel()
        payment.paymentMethod!!.id = "paymentMethod"

        presenter = CardIssuersPresenter(payment, view, interactor)
    }

    @Test
    fun getPaymentMethods() {
        presenter.getCardIssuers()
        verify(view, times(1)).showSkeleton()
        verify(interactor, times(1)).getCardIssuers(eq("paymentMethod"), any())
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
    fun findCardIssuer() {
        presenter.organizeLists(cardIssuers)
        Assert.assertEquals(presenter.findCardIssuer(ImageTextModel("bankOne", "bankOne", "someUrl")), cardIssuers[0])
    }

    @Test
    fun organizeLists() {
        presenter.organizeLists(cardIssuers)
        assertEquals(3, presenter.cardIssuers.size)
        assertEquals(3, presenter.imageTextsList.size)
        verify(interactor, times(2)).getThumbnail(any(), any())
    }

    private fun generateCardIssuers(): ArrayList<CardIssuerModel> {
        val cardIssuers = ArrayList<CardIssuerModel>()

        val bankOne = CardIssuerModel()
        bankOne.thumbnailURL = "someUrl"
        bankOne.id = "bankOne"
        bankOne.name = "bankOne"
        cardIssuers.add(bankOne)

        val bankTwo = CardIssuerModel()
        bankTwo.id = "bankTwo"
        bankTwo.name = "bankTwo"
        bankTwo.thumbnailURL = "anotherUrl"
        cardIssuers.add(bankTwo)

        val bankNoThumbnail = CardIssuerModel()
        bankNoThumbnail.id = "bankNoThumbnail"
        bankNoThumbnail.name = "bankNoThumbnail"
        cardIssuers.add(bankNoThumbnail)

        return cardIssuers
    }
}