package com.rodriguezmauro1994.pagosmobile.model

import android.graphics.Bitmap
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by ROD
 */
class CardIssuerModel: Serializable {
    var id: String? = null
    var name: String? = null
    var thumbnailImage: Bitmap? = null
    @SerializedName("secure_thumbnail")
    var thumbnailURL: String? = ""
}