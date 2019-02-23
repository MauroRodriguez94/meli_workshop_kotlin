package com.rodriguezmauro1994.pagosmobile.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.rodriguezmauro1994.pagosmobile.R

import java.util.ArrayList

/**
 * Created by ROD
 */
class EmptyStateView(private val mInflater: LayoutInflater, private val mParent: ViewGroup) {
    private val lAlreadyHiddenViews: MutableList<Int>
    private var emptyState: View? = null
    private var rLayout: Int = 0

    init {
        this.lAlreadyHiddenViews = ArrayList()
        this.rLayout = R.layout.fragment_empty_state
    }

    fun show(rImageId: Int, sText: String) {
        emptyState = inflateEmptyState()

        (emptyState!!.findViewById<View>(R.id.ivTop) as ImageView).setImageResource(rImageId)
        emptyState!!.findViewById<View>(R.id.ivTop).visibility = View.VISIBLE

        (emptyState!!.findViewById<View>(R.id.tvText) as TextView).text = sText
        emptyState!!.findViewById<View>(R.id.tvText).visibility = View.VISIBLE
    }

    fun hide() {
        if (!hasEmptyState()) {
            for (i in mParent.childCount - 1 downTo 0) {
                if (mParent.getChildAt(i).id == R.id.llEmptyState) {
                    mParent.removeViewAt(i)
                } else {
                    val wasntHide = this.lAlreadyHiddenViews.none { it == mParent.getChildAt(i).id }

                    if (wasntHide) {
                        mParent.getChildAt(i).visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    private fun hasEmptyState(): Boolean {
        return (0 until mParent.childCount).any { mParent.getChildAt(it).id == R.id.llEmptyState }
    }

    private fun inflateEmptyState(): View {
        this.hideChildren()
        return mInflater.inflate(this.rLayout, mParent)
    }

    private fun hideChildren() {
        for (i in 0 until mParent.childCount) {
            if (this.mParent.getChildAt(i).visibility == View.GONE) {
                this.lAlreadyHiddenViews.add(this.mParent.getChildAt(i).id)
            } else {
                this.mParent.getChildAt(i).visibility = View.GONE
            }
        }
    }

    fun setLayout(rLayout: Int) {
        this.rLayout = rLayout
    }
}
