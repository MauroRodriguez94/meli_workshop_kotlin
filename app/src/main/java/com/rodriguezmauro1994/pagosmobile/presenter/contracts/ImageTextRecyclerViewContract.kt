package com.rodriguezmauro1994.pagosmobile.presenter.contracts

import android.graphics.Bitmap
import com.rodriguezmauro1994.pagosmobile.model.ImageTextModel

interface ImageTextRecyclerViewContract: GenericRecyclerViewContract {
    fun loadImageTextAdapter(imageTexts: ArrayList<ImageTextModel>)
    fun showThumbnail(id: String, thumbnail: Bitmap)
}