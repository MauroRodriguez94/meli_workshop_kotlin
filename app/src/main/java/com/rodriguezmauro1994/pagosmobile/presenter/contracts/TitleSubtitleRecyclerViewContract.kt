package com.rodriguezmauro1994.pagosmobile.presenter.contracts

import com.rodriguezmauro1994.pagosmobile.model.TitleSubtitleModel

interface TitleSubtitleRecyclerViewContract: GenericRecyclerViewContract {
    fun loadTitleSubtitleAdapter(list: ArrayList<TitleSubtitleModel>)
}