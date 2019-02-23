package com.rodriguezmauro1994.pagosmobile.interfaces

/**
 * Created by ROD
 */
interface RequestCallback {
    fun onResponseOk(vararg objs: Any)
    fun onResponseFailed(vararg objs: Any)
}
