package com.mobiquity.code.challenge.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.mobiquity.code.challenge.R
import com.mobiquity.code.challenge.di.component.ActivityComponent
import com.mobiquity.code.challenge.ui.base.BaseActivity
import com.mobiquity.code.challenge.ui.home.HomeFragment
import com.mobiquity.code.challenge.ui.map.MapActivity
import com.mobiquity.code.challenge.ui.help.HelpFragment
import com.mobiquity.code.challenge.ui.settings.SettingsFragment
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity<MainViewModel>() {
    companion object {
        const val TAG = "MainActivity"
    }

    @Inject
    lateinit var mainSharedViewModel: MainSharedViewModel


    private var activeFragment: Fragment? = null

    override fun provideLayoutId(): Int = R.layout.activity_main

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }


    override fun setupView(savedInstanceState: Bundle?) {
        bottomNavigation.run {
            itemIconTintList = null
            setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.itemHome -> {
                        viewModel.onHomeSelected()
                        true
                    }
                    R.id.itemAddPhotos -> {
                        viewModel.onPhotoSelected()
                        true
                    }
                    R.id.itemProfile -> {
                        viewModel.onProfileSelected()
                        true
                    }
                    else -> false
                }
            }
        }
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.homeNavigation.observe(this, Observer {
            it.getIfNotHandled()?.run { showHome() }
        })

        viewModel.profileNavigation.observe(this, Observer {
            it.getIfNotHandled()?.run { showProfile() }
        })

        viewModel.photoNavigation.observe(this, Observer {
            it.getIfNotHandled()?.run { showAddPhoto() }
        })

        mainSharedViewModel.mapRedirection.observe(this, Observer {
            it.getIfNotHandled()?.run { showMap() }
        })

        viewModel.allUser.observe(this, Observer {
            mainSharedViewModel.homeData.postValue(it)
        })


    }

    private fun showMap() {
        startActivity(Intent(applicationContext, MapActivity::class.java))
    }

    private fun showHome() {
        if (activeFragment is HomeFragment) return

        val fragmentTransaction = supportFragmentManager.beginTransaction()

        var fragment = supportFragmentManager.findFragmentByTag(HomeFragment.TAG) as HomeFragment?

        if (fragment == null) {
            fragment = HomeFragment.newInstance()
            fragmentTransaction.add(R.id.containerFragment, fragment, HomeFragment.TAG)
        } else {
            fragmentTransaction.show(fragment)
        }

        if (activeFragment != null) fragmentTransaction.hide(activeFragment as Fragment)

        fragmentTransaction.commit()

        activeFragment = fragment
    }

    private fun showProfile() {
        if (activeFragment is SettingsFragment) return

        val fragmentTransaction = supportFragmentManager.beginTransaction()

        var fragment =
            supportFragmentManager.findFragmentByTag(SettingsFragment.TAG) as SettingsFragment?

        if (fragment == null) {
            fragment = SettingsFragment.newInstance()
            fragmentTransaction.add(R.id.containerFragment, fragment, SettingsFragment.TAG)
        } else {
            fragmentTransaction.show(fragment)
        }

        if (activeFragment != null) fragmentTransaction.hide(activeFragment as Fragment)

        fragmentTransaction.commit()

        activeFragment = fragment
    }

    private fun showAddPhoto() {
        if (activeFragment is HelpFragment) return

        val fragmentTransaction = supportFragmentManager.beginTransaction()

        var fragment = supportFragmentManager.findFragmentByTag(HelpFragment.TAG) as HelpFragment?

        if (fragment == null) {
            fragment = HelpFragment.newInstance()
            fragmentTransaction.add(R.id.containerFragment, fragment, HelpFragment.TAG)
        } else {
            fragmentTransaction.show(fragment)
        }

        if (activeFragment != null) fragmentTransaction.hide(activeFragment as Fragment)

        fragmentTransaction.commit()

        activeFragment = fragment
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        Log.e("onResume main", "yes")
        viewModel.getLocations()
    }
}