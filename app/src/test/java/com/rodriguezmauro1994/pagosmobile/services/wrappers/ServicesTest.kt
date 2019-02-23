package com.rodriguezmauro1994.pagosmobile.services.wrappers

import com.rodriguezmauro1994.pagosmobile.model.Methods
import com.rodriguezmauro1994.pagosmobile.model.ParamsModel
import com.rodriguezmauro1994.pagosmobile.utils.Constants
import org.junit.Test

import org.junit.Assert.*

/**
 * Created by ROD
 */
class ServicesTest {
    @Test
    fun generateURLNoParams() {
        val actualUrl = Services().generateURL(Methods.PAYMENT, "testFunction")
        val expectedUrl = Constants.URL + "payment_methods/testFunction?" + Constants.API_KEY_NAME + "=" + Constants.API_KEY + "&"
        assertEquals(expectedUrl, actualUrl)
    }

    @Test
    fun generateURLParam() {
        val params = ArrayList<ParamsModel>()
        params.add(ParamsModel("test", "value"))

        val actualUrl = Services().generateURL(Methods.PAYMENT, "testFunction", params)
        val expectedUrl = Constants.URL + "payment_methods/testFunction?test=value&" + Constants.API_KEY_NAME + "=" + Constants.API_KEY + "&"
        assertEquals(expectedUrl, actualUrl)
    }

    @Test
    fun generateURLParams() {
        val params = ArrayList<ParamsModel>()
        params.add(ParamsModel("test", "value"))
        params.add(ParamsModel("testParam2", "valueParam2"))

        val actualUrl = Services().generateURL(Methods.PAYMENT, "testFunction", params)
        val expectedUrl = Constants.URL + "payment_methods/testFunction?test=value&testParam2=valueParam2&" + Constants.API_KEY_NAME + "=" + Constants.API_KEY + "&"
        assertEquals(expectedUrl, actualUrl)
    }
}