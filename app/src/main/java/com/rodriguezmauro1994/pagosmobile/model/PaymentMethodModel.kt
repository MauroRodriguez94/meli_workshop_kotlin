package com.rodriguezmauro1994.pagosmobile.model

import android.graphics.Bitmap
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by ROD
 */
class PaymentMethodModel: Serializable {
    var id: String? = null
    var name: String? = null
    var thumbnailImage: Bitmap? = null
    var status: String? = null
    @SerializedName("secure_thumbnail")
    var thumbnailURL: String? = ""
    @SerializedName("payment_type_id")
    var type: String? = null
}