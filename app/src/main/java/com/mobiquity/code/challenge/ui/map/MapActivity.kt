package com.mobiquity.code.challenge.ui.map

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.mobiquity.code.challenge.R
import com.mobiquity.code.challenge.di.component.ActivityComponent
import com.mobiquity.code.challenge.ui.base.BaseActivity
import com.mobiquity.code.challenge.ui.main.MainSharedViewModel
import java.io.IOException
import java.util.*
import javax.inject.Inject


class MapActivity : BaseActivity<MapViewModel>()
    , OnMapReadyCallback, PermissionListener, GoogleMap.OnMarkerClickListener {

    private lateinit var googleMap: GoogleMap
    override fun provideLayoutId(): Int = R.layout.activity_map

    @Inject
    lateinit var mainSharedViewModel: MainSharedViewModel
    override fun injectDependencies(activityComponent: ActivityComponent) =
        activityComponent.inject(this)

    override fun setupView(savedInstanceState: Bundle?) {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)
    }

    override fun setupObservers() {
        super.setupObservers()
        viewModel.location.observe(this, androidx.lifecycle.Observer {
            it.getIfNotHandled()?.run {
                backToMainActivity()
            }
        })
    }

    private fun backToMainActivity() {
        finish()
    }

    override fun onMapReady(map: GoogleMap?) {
        googleMap = map ?: return
        if (isPermissionGiven()) {
            showUI()
            googleMap.setOnMapClickListener {
                googleMap.clear()
                addMarker(it)
            }
            googleMap.setOnMarkerClickListener(this)
        } else {
            givePermission()
        }
    }

    override fun onPermissionGranted(response: PermissionGrantedResponse?) {
        showUI()
    }

    override fun onPermissionRationaleShouldBeShown(
        permission: PermissionRequest?,
        token: PermissionToken?
    ) {
        token!!.continuePermissionRequest()
    }

    override fun onPermissionDenied(response: PermissionDeniedResponse?) {
        showMessage("Permission required for showing location")
        finish()
    }

    override fun onMarkerClick(marker: Marker?): Boolean {
        showAlert(marker)
        return true
    }

    private fun showAlert(marker: Marker?) {
        this.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(R.string.add_location_confirm)
            builder.apply {
                setPositiveButton(
                    R.string.ok
                ) { _, _ ->
                    // User clicked OK button
                    viewModel.saveLocation(marker!!)
                }
                setNegativeButton(R.string.cancel) { _, _ ->

                }
            }.show()
            builder.create()
        }
    }

    private fun isPermissionGiven(): Boolean {
        return ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun addMarker(latLng: LatLng) {
        val gcd = Geocoder(this, Locale.getDefault())
        val addresses: List<Address>
        var address = "No Unknown Address"
        try {
            addresses = gcd.getFromLocation(
                latLng.latitude,
                latLng.longitude,
                1
            )
            if (addresses.isNotEmpty()) {
                address = addresses[0].getAddressLine(0)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        val icon = BitmapDescriptorFactory.fromBitmap(
            BitmapFactory.decodeResource(
                this.resources,
                R.drawable.ic_pickup
            )
        )
        googleMap.addMarker(
            MarkerOptions()
                .position(LatLng(latLng.latitude, latLng.longitude))
                .title("Current Location")
                .snippet(address)
                .icon(icon)
        )

        val cameraPosition = CameraPosition.Builder()
            .target(LatLng(latLng.latitude, latLng.longitude))
            .zoom(17f)
            .build()
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }

    private fun givePermission() {
        Dexter.withActivity(this)
            .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
            .withListener(this)
            .check()
    }

    @SuppressLint("MissingPermission")
    fun showUI() {
        googleMap.isMyLocationEnabled = true
        googleMap.uiSettings.isMyLocationButtonEnabled = true
        googleMap.uiSettings.isZoomControlsEnabled = true
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }
}