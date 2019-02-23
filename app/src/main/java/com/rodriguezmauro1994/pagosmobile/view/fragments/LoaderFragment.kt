package com.rodriguezmauro1994.pagosmobile.view.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rodriguezmauro1994.pagosmobile.R
import com.rodriguezmauro1994.pagosmobile.interfaces.LoaderCallback
import com.rodriguezmauro1994.pagosmobile.utils.LoadingUtils
import kotlinx.android.synthetic.main.fragment_loader.*

/**
 * Created by ROD
 */
class LoaderFragment: Fragment() {
    private lateinit var loadingUtils: LoadingUtils
    private lateinit var loaderCallback: LoaderCallback

    companion object {
        fun getInstance(callback: LoaderCallback): LoaderFragment {
            val instance = LoaderFragment()
            instance.loaderCallback = callback

            return instance
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            = inflater.inflate(R.layout.fragment_loader, container, false)!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
        loaderCallback.makeTransaction()
    }

    private fun initialize() {
        loadingUtils = LoadingUtils(llParentLoading, context!!)
    }

    fun onCompleteTransaction(operationOk: Boolean, message: String) {
        this.loadingUtils.setComplete(operationOk, message)
        this.initializeButtons()
    }

    private fun initializeButtons() {
        btConfirm.setOnClickListener { loaderCallback.onActionClicked() }
    }
}