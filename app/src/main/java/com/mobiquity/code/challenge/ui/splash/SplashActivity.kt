package com.mobiquity.code.challenge.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.mobiquity.code.challenge.R
import com.mobiquity.code.challenge.di.component.ActivityComponent
import com.mobiquity.code.challenge.ui.base.BaseActivity
import com.mobiquity.code.challenge.ui.main.MainActivity
import com.mobiquity.code.challenge.utils.common.Event

class SplashActivity : BaseActivity<SplashViewModel>() {

    companion object {
        const val TAG = "SplashActivity"
    }

    override fun provideLayoutId(): Int = R.layout.activity_splash

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {
    }

    override fun setupObservers() {
        super.setupObservers()


        viewModel.launchMain.observe(this, Observer<Event<Map<String, String>>> {
            it.getIfNotHandled()?.run {
                startActivity(Intent(applicationContext, MainActivity::class.java))
            }
        })
    }
}