package com.rodriguezmauro1994.pagosmobile.services.wrappers

import com.rodriguezmauro1994.pagosmobile.model.Methods
import com.rodriguezmauro1994.pagosmobile.model.ParamsModel
import com.rodriguezmauro1994.pagosmobile.utils.Constants

/**
 * Created by ROD
 */
open class Services {
    fun sendRequest(url: String, volleyWrapper: VolleyWrapper) {
        volleyWrapper.getRequest(url)
    }

    fun sendImageRequest(url: String, id: String, volleyWrapper: VolleyWrapper,  maxWidth: Int = 0, maxHeight: Int = 0) {
        volleyWrapper.getImage(url, id, maxWidth, maxHeight)
    }

    fun generateURL(method: Methods, function: String, params: ArrayList<ParamsModel> = ArrayList()): String {
        val builderUrl = generateBaseURL(method, function)
        addPublicKeyToParams(params)
        appendParams(builderUrl, params)

        return builderUrl.toString()
    }

    private fun generateBaseURL(method: Methods, function: String): StringBuilder {
        val builderUrl = StringBuilder(Constants.URL)
        addMethod(method, builderUrl)
        appendFunction(function, builderUrl)

        return builderUrl
    }

    private fun addMethod(method: Methods, builderUrl: StringBuilder) {
        builderUrl.append(Constants.METHODS[method])
    }

    private fun appendFunction(function: String, builderUrl: StringBuilder) {
        if (function.isNotEmpty()) {
            builderUrl.append("/")
            builderUrl.append(function)
        }
    }

    private fun addPublicKeyToParams(params: ArrayList<ParamsModel>) {
        params.add(ParamsModel(Constants.API_KEY_NAME, Constants.API_KEY))
    }

    private fun appendParams(builderUrl: StringBuilder, params: ArrayList<ParamsModel>) {
        builderUrl.append("?")
        for (param in params) {
            appendParam(builderUrl, param)
        }
    }

    private fun appendParam(builderUrl: StringBuilder, param: ParamsModel) {
        builderUrl.append(param.name)
        builderUrl.append("=")
        builderUrl.append(param.value)
        builderUrl.append("&")
    }
}
