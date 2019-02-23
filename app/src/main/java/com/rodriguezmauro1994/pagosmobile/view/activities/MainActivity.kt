package com.rodriguezmauro1994.pagosmobile.view.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.rodriguezmauro1994.pagosmobile.R
import com.rodriguezmauro1994.pagosmobile.model.Payment
import com.rodriguezmauro1994.pagosmobile.view.fragments.AmountFragment
import com.rodriguezmauro1994.pagosmobile.view.fragments.SummaryFragment

/**
 * Created by ROD
 */
class MainActivity : AppCompatActivity() {
    var payment: Payment? = null
    var showResume = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadAmountFragment()
    }

    private fun loadAmountFragment() {
        supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, AmountFragment())
                .commit()
    }

    fun goToFragment(fragment: Fragment, homeButtonVisible: Boolean = true){
        toggleHomeButton(homeButtonVisible)
        supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack(fragment.javaClass.name)
                .commit()
    }

    fun toggleHomeButton(enabled: Boolean) {
        supportActionBar!!.setDisplayHomeAsUpEnabled(enabled)
        supportActionBar!!.setDisplayShowHomeEnabled(enabled)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount == 1) {
            homeFragmentActions()
        }

        supportFragmentManager.popBackStack()
    }

    private fun homeFragmentActions() {
        toggleHomeButton(false)
        verifyShowResume()
    }

    private fun verifyShowResume() {
        if(showResume) {
            showResume = false
            goToSummary()
        }
    }

    private fun goToSummary() {
        val fragment = SummaryFragment()

        val extras = Bundle()
        extras.putSerializable("payment", payment)
        fragment.arguments = extras

        goToFragment(fragment)
    }

    fun backToFragment(positionInBackStack: Int) {
        for (i in supportFragmentManager.backStackEntryCount downTo positionInBackStack + 1) {
            onBackPressed()
        }

        if(positionInBackStack == 0) {
            homeFragmentActions()
        }
    }
}
