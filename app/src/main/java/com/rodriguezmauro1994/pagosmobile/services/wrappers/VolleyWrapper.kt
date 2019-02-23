package com.rodriguezmauro1994.pagosmobile.services.wrappers

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.rodriguezmauro1994.pagosmobile.interfaces.RequestCallback

/**
 * Created by ROD
 */
class VolleyWrapper private constructor() {
    private lateinit var queue: RequestQueue
    private lateinit var callback: RequestCallback

    companion object {
        private val instance: VolleyWrapper = VolleyWrapper()
        fun getInstance(context: Context, callback: RequestCallback): VolleyWrapper {
            instance.queue = Volley.newRequestQueue(context)
            instance.callback = callback

            return instance
        }
    }

    fun getRequest(url: String) {
        val getRequest = StringRequest(Request.Method.GET, url,
                Response.Listener { response ->
                    callback.onResponseOk(response)
                },
                Response.ErrorListener { error ->
                    callback.onResponseFailed(error)
                })

        queue.add(getRequest)
    }

    fun getImage(url: String, id: String, maxWith: Int = 0, maxHeight: Int = 0) {
        val imageRequest = ImageRequest(url,
                Response.Listener { response ->
                    callback.onResponseOk(id, response)
                }, maxWith, maxHeight, ImageView.ScaleType.FIT_CENTER, Bitmap.Config.RGB_565,
                Response.ErrorListener { error ->
                    callback.onResponseFailed(error)
                })

        queue.add(imageRequest)
    }
}
