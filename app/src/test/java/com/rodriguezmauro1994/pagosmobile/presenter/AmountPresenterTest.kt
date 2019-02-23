package com.rodriguezmauro1994.pagosmobile.presenter

import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.rodriguezmauro1994.pagosmobile.presenter.contracts.AmountContract
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
class AmountPresenterTest {
    @Mock
    private lateinit var view: AmountContract.View
    private lateinit var presenter: AmountPresenter

    @Before
    fun setUp() {
        presenter = AmountPresenter(view)
    }

    @Test
    fun toggleConfirmButtonEnable() {
        presenter.toggleConfirmButton(2.0)
        verify(view, times(1)).enableConfirmButton()
        verify(view, never()).disableConfirmButton()
    }

    @Test
    fun toggleConfirmButtonDisableZero() {
        presenter.toggleConfirmButton(0.0)
        verify(view, times(1)).disableConfirmButton()
        verify(view, never()).enableConfirmButton()
    }

    @Test
    fun toggleConfirmButtonDisableNegative() {
        presenter.toggleConfirmButton(-2.0)
        verify(view, times(1)).disableConfirmButton()
        verify(view, never()).enableConfirmButton()
    }
}