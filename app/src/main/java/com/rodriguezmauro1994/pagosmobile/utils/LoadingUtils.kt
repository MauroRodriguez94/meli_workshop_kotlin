package com.rodriguezmauro1994.pagosmobile.utils

import android.animation.Animator
import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.rodriguezmauro1994.pagosmobile.R

/**
 * Created by ROD
 */
class LoadingUtils(private val llParentLoading: View, private val context: Context) {
    /*failedView;*/
    private lateinit var loadingView: LottieAnimationView
    private lateinit var completeView: LottieAnimationView
    private lateinit var tvDescription: TextView
    private lateinit var llButtons: LinearLayout
    private var complete: Int = 0

    init {
        setUpAnimations()
        this.llButtons.visibility = View.GONE
    }

    private fun setUpAnimations() {
        initialize()
        setAnimationsJson()
        handleEvent()
    }

    private fun handleEvent() {
        this.loadingView.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animator: Animator) {
                if (complete == 1) {
                    finishLoading()
                    showOperationDetails(true)
                } else if (complete == -1) {
                    finishLoading()
                    showOperationDetails(false)
                }
            }

            override fun onAnimationStart(animation: Animator, isReverse: Boolean) {}
            override fun onAnimationEnd(animation: Animator, isReverse: Boolean) {}
            override fun onAnimationStart(animator: Animator) {}
            override fun onAnimationEnd(animator: Animator) {}
            override fun onAnimationCancel(animator: Animator) {}
        })
    }

    private fun setAnimationsJson() {
        this.completeView.setAnimation("ic_success.json", LottieAnimationView.CacheStrategy.Strong)
        //this.failedView.setAnimation("ic_failed.json", LottieAnimationView.CacheStrategy.Strong);
        this.loadingView.setAnimation("ic_loading.json")
        this.loadingView.repeatCount = LottieDrawable.INFINITE
        this.loadingView.scale = 2f
        this.loadingView.playAnimation()
    }

    private fun initialize() {
        this.llButtons = llParentLoading.findViewById(R.id.llButtons)
        this.tvDescription = llParentLoading.findViewById(R.id.tvDescription)
        this.completeView = llParentLoading.findViewById(R.id.lvComplete)
        this.loadingView = llParentLoading.findViewById(R.id.lvLoading)
        //this.failedView = llParentLoading.findViewById(R.id.lvFailed);
    }

    private fun finishLoading() {
        loadingView.cancelAnimation()
        loadingView.visibility = View.GONE
    }

    private fun showOperationDetails(operationOk: Boolean) {
        //showCompleteAnimation(operationOk ? completeView : failedView);
        showCompleteAnimation(completeView)
        tvDescription.visibility = View.VISIBLE
        llButtons.visibility = View.VISIBLE

        if (!operationOk) {
            llParentLoading.setBackgroundColor(ContextCompat.getColor(context, R.color.transactionFailed))
        }
    }

    private fun showCompleteAnimation(animationView: LottieAnimationView) {
        animationView.visibility = View.VISIBLE
        animationView.scale = 0.8f
        animationView.repeatCount = 0
        animationView.playAnimation()
    }

    fun setComplete(successfullyComplete: Boolean, description: String) {
        this.complete = if (successfullyComplete) 1 else -1
        this.tvDescription.text = description
    }
}
