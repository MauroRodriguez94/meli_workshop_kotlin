package com.rodriguezmauro1994.pagosmobile.interfaces

import com.rodriguezmauro1994.pagosmobile.model.ImageTextModel
import com.rodriguezmauro1994.pagosmobile.model.TitleSubtitleModel

/**
 * Created by ROD
 */
interface OnItemClickCallback {
    fun onClick(imageText: ImageTextModel) {}
    fun onClick(titleSubtitle: TitleSubtitleModel) {}
}