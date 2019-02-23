package com.rodriguezmauro1994.pagosmobile.view.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.rodriguezmauro1994.pagosmobile.R
import com.rodriguezmauro1994.pagosmobile.interfaces.OnItemClickCallback
import com.rodriguezmauro1994.pagosmobile.model.TitleSubtitleModel

/**
 * Created by ROD
 */
class TitleSubtitleAdapter(private val list: ArrayList<TitleSubtitleModel>,
                           private val onClickCallback: OnItemClickCallback): RecyclerView.Adapter<TitleSubtitleAdapter.TitleSubtitleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TitleSubtitleViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_title_subtitle, parent, false)
        return TitleSubtitleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TitleSubtitleViewHolder, position: Int) {
        val item = list[position]
        holder.tvTitle.text = item.title
        holder.tvSubtitle.text = item.subtitle

        holder.llItem.setOnClickListener {
            onClickCallback.onClick(item)
        }
    }

    class TitleSubtitleViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvSubtitle: TextView = view.findViewById(R.id.tvSubtitle)
        val llItem: LinearLayout = view.findViewById(R.id.llItem)
    }
}