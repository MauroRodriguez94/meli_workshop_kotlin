package com.rodriguezmauro1994.pagosmobile.view.fragments

import android.graphics.Bitmap
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ethanhua.skeleton.Skeleton
import com.ethanhua.skeleton.SkeletonScreen
import com.rodriguezmauro1994.pagosmobile.R
import com.rodriguezmauro1994.pagosmobile.interfaces.OnItemClickCallback
import com.rodriguezmauro1994.pagosmobile.interfaces.SimpleCallback
import com.rodriguezmauro1994.pagosmobile.model.ImageTextModel
import com.rodriguezmauro1994.pagosmobile.model.TitleSubtitleModel
import com.rodriguezmauro1994.pagosmobile.presenter.contracts.ImageTextRecyclerViewContract
import com.rodriguezmauro1994.pagosmobile.presenter.contracts.TitleSubtitleRecyclerViewContract
import com.rodriguezmauro1994.pagosmobile.utils.EmptyStateView
import com.rodriguezmauro1994.pagosmobile.view.adapters.ImageTextAdapter
import com.rodriguezmauro1994.pagosmobile.view.adapters.TitleSubtitleAdapter
import kotlinx.android.synthetic.main.fragment_generic_recyclerview.*

/**
 * Created by ROD
 */
class GenericRecyclerViewFragment: Fragment(), ImageTextRecyclerViewContract, TitleSubtitleRecyclerViewContract {
    private lateinit var imageTextAdapter: ImageTextAdapter
    private lateinit var titleSubtitleAdapter: TitleSubtitleAdapter
    private lateinit var onItemClick: OnItemClickCallback
    private lateinit var onViewCreated: SimpleCallback
    private var title: Int? = null
    private var imageHolder: Int? = null
    private var skeleton: SkeletonScreen? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            = inflater.inflate(R.layout.fragment_generic_recyclerview, container, false)!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
        onViewCreated.onCallback()
    }

    private fun initialize() {
        tvTitleList.text = getString(title!!)
        rvList.layoutManager = LinearLayoutManager(context)
    }

    override fun loadImageTextAdapter(imageTexts: ArrayList<ImageTextModel>) {
        imageTextAdapter = ImageTextAdapter(imageTexts, imageHolder!!, onItemClick)
        rvList.adapter = imageTextAdapter
    }

    override fun loadTitleSubtitleAdapter(list: ArrayList<TitleSubtitleModel>) {
        titleSubtitleAdapter = TitleSubtitleAdapter(list, onItemClick)
        rvList.adapter = titleSubtitleAdapter
    }

    override fun showThumbnail(id: String, thumbnail: Bitmap) {
        imageTextAdapter.updateImage(id, thumbnail)
    }

    override fun showSkeleton() {
        skeleton = Skeleton.bind(rvList)
                .duration(1200)
                .load(R.layout.item_skeleton_image_text)
                .show()
    }

    override fun hideSkeleton() {
        skeleton?.hide()
    }

    override fun showEmptyState(resString: Int) {
        EmptyStateView(layoutInflater, llMainList).show(R.drawable.ic_connection_error, getString(resString))
    }

    override fun showEmptyState(placeHolder: Int, replace: Int) {
        EmptyStateView(layoutInflater, llMainList).show(R.drawable.ic_connection_error, getString(placeHolder, getString(replace)))
    }

    override fun showErrorEmptyState() {
        EmptyStateView(layoutInflater, llMainList).show(R.drawable.ic_not_found, getString(R.string.empty_state_payment_method))
    }

    companion object {
        fun getInstance(title: Int, imageHolder: Int? = null, onItemClick: OnItemClickCallback, onViewCreated: SimpleCallback): GenericRecyclerViewFragment {
            val fragment = GenericRecyclerViewFragment()
            fragment.title = title
            fragment.imageHolder = imageHolder
            fragment.onItemClick = onItemClick
            fragment.onViewCreated = onViewCreated

            return fragment
        }
    }
}
