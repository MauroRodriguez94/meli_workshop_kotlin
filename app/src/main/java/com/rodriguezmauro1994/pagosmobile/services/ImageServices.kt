package com.rodriguezmauro1994.pagosmobile.services

import android.content.Context
import android.graphics.Bitmap
import com.rodriguezmauro1994.pagosmobile.interfaces.RequestCallback
import com.rodriguezmauro1994.pagosmobile.model.ImageTextModel
import com.rodriguezmauro1994.pagosmobile.services.wrappers.Services
import com.rodriguezmauro1994.pagosmobile.services.wrappers.VolleyWrapper

/**
 * Created by ROD
 */
class ImageServices(private val context: Context): Services() {
    fun getThumbnail(imageText: ImageTextModel, callback: RequestCallback) {
        sendImageRequest(imageText.thumbnailURL, imageText.id, VolleyWrapper.getInstance(context, object: RequestCallback {
            override fun onResponseOk(vararg objs: Any) {
                try {
                    val id = objs[0] as String
                    val image = objs[1] as Bitmap
                    callback.onResponseOk(id, image)
                } catch (e: Exception) {
                    callback.onResponseFailed()
                }
            }

            override fun onResponseFailed(vararg objs: Any) {
                callback.onResponseFailed()
            }
        }))
    }
}