package com.rodriguezmauro1994.pagosmobile.presenter.contracts

/**
 * Created by ROD
 */
interface GenericRecyclerViewContract {
    fun showSkeleton()
    fun hideSkeleton()
    fun showEmptyState(resString: Int)
    fun showErrorEmptyState()
    fun showEmptyState(placeHolder: Int, replace: Int)
}