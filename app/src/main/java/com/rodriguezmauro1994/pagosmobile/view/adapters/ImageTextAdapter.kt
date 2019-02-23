package com.rodriguezmauro1994.pagosmobile.view.adapters

import android.graphics.Bitmap
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.rodriguezmauro1994.pagosmobile.R
import com.rodriguezmauro1994.pagosmobile.interfaces.OnItemClickCallback
import com.rodriguezmauro1994.pagosmobile.model.ImageTextModel

/**
 * Created by ROD
 */
class ImageTextAdapter(private val list: ArrayList<ImageTextModel>,
                       private val holderImage: Int,
                       private val onClickCallback: OnItemClickCallback): RecyclerView.Adapter<ImageTextAdapter.ImageTextViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageTextViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_image_text, parent, false)
        return ImageTextViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ImageTextViewHolder, position: Int) {
        val imageText = list[position]
        if(imageText.thumbnailImage == null){
            holder.ivImage.setImageResource(holderImage)
        } else {
            holder.ivImage.setImageBitmap(imageText.thumbnailImage)
        }

        holder.tvText.text = imageText.name
        holder.llItem.setOnClickListener {
            onClickCallback.onClick(imageText)
        }
    }

    class ImageTextViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val ivImage: ImageView = view.findViewById(R.id.ivImage)
        val tvText: TextView = view.findViewById(R.id.tvText)
        val llItem: LinearLayout = view.findViewById(R.id.llItem)
    }

    fun updateImage(id: String, thumbnail: Bitmap) {
        val index = list.indexOfFirst { it -> it.id == id }
        list[index].thumbnailImage = thumbnail
        notifyItemChanged(index)
    }
}