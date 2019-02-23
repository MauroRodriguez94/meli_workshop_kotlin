package com.rodriguezmauro1994.pagosmobile.view.fragments

import com.rodriguezmauro1994.pagosmobile.R
import com.rodriguezmauro1994.pagosmobile.interfaces.LoaderCallback
import com.rodriguezmauro1994.pagosmobile.interfaces.OnItemClickCallback
import com.rodriguezmauro1994.pagosmobile.interfaces.SimpleCallback
import com.rodriguezmauro1994.pagosmobile.model.Payment
import com.rodriguezmauro1994.pagosmobile.model.TitleSubtitleModel
import com.rodriguezmauro1994.pagosmobile.presenter.InstallmentsPresenter
import com.rodriguezmauro1994.pagosmobile.presenter.contracts.InstallmentsContract
import com.rodriguezmauro1994.pagosmobile.services.InstallmentsServices
import com.rodriguezmauro1994.pagosmobile.view.activities.MainActivity

/**
 * Created by ROD
 */
class InstallmentsView (private var payment: Payment, private var activity: MainActivity): InstallmentsContract.View {
    private lateinit var fragment: GenericRecyclerViewFragment
    private lateinit var presenter: InstallmentsContract.Presenter
    private lateinit var loader: LoaderFragment

    fun show() {
        fragment = GenericRecyclerViewFragment.getInstance(R.string.installments_title, null,
                object : OnItemClickCallback {
                    override fun onClick(titleSubtitle: TitleSubtitleModel) {
                        payment.payerCost = presenter.findPayerCost(titleSubtitle)
                        confirmPayment()
                    }
                },
                object : SimpleCallback {
                    override fun onCallback() {
                        presenter.getInstallments()
                    }
                })

        activity.goToFragment(fragment)
        presenter = InstallmentsPresenter(payment, this, InstallmentsServices(activity))
    }

    private fun confirmPayment() {
        loader = LoaderFragment.getInstance(object: LoaderCallback {
            override fun makeTransaction() {
                presenter.confirmPayment(payment)
            }

            override fun onActionClicked() {
                presenter.onActionClicked()
            }
        })

        activity.goToFragment(loader)
        activity.toggleHomeButton(false)
    }

    override fun onTransactionOk() {
        loader.onCompleteTransaction(true, activity.getString(R.string.transactionOk))
    }

    override fun onTransactionFailed() {
        loader.onCompleteTransaction(false, activity.getString(R.string.transactionFailed))
    }

    override fun backToHome() {
        setPayment()
        goToHomeFragment()
    }

    private fun goToHomeFragment() {
        activity.backToFragment(0)
    }

    private fun setPayment() {
        activity.showResume = true
        activity.payment = payment
    }

    override fun showSkeleton() {
        fragment.showSkeleton()
    }

    override fun hideSkeleton() {
        fragment.hideSkeleton()
    }

    override fun loadTitleSubtitleAdapter(list: ArrayList<TitleSubtitleModel>) {
        fragment.loadTitleSubtitleAdapter(list)
    }

    override fun showEmptyState(resString: Int) {
        fragment.showEmptyState(resString)
    }

    override fun showErrorEmptyState() {
        fragment.showErrorEmptyState()
    }

    override fun showEmptyState(placeHolder: Int, replace: Int) {
        fragment.showEmptyState(placeHolder, replace)
    }
}
