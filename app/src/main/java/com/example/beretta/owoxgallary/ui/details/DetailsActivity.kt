package com.example.beretta.owoxgallary.ui.details

import android.Manifest
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.beretta.owoxgallary.R
import com.example.beretta.owoxgallary.arch.view.BaseArchActivity
import com.example.beretta.owoxgallary.models.network.response.DownloadLinkResult
import com.example.beretta.owoxgallary.models.network.response.PhotoRest
import com.example.beretta.owoxgallary.network.service.DownloadService
import com.example.beretta.owoxgallary.utils.GsonHolder

import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : BaseArchActivity<DetailsContract.View, DetailsContract.ViewModel>(),
        DetailsContract.View {


    companion object {
        const val KEY_PHOTO = "ext_k_photo"
        const val PERNISSION_RQT_CODE = 7654
        fun getIntent(ctx: Context, photo: PhotoRest): Intent {
            val intent = Intent(ctx, DetailsActivity::class.java)
            val json = GsonHolder.gson.toJson(photo, PhotoRest::class.java)
            intent.putExtra(KEY_PHOTO, json)
            return intent
        }
    }

    val selectedPhoto by lazy { GsonHolder.gson.fromJson(intent.getStringExtra(KEY_PHOTO), PhotoRest::class.java) }

    override fun getRootView(): View = activity_details_root

    override fun getViewModel(): DetailsContract.ViewModel = ViewModelProviders.of(this).get(DetailsViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        deserializeIntent()
        setupLayouts()
    }

    private fun setupLayouts() {
        Glide.with(this)
                .load(selectedPhoto.urls.regular)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(activity_details_image)

        selectedPhoto?.user?.username?.let { supportActionBar?.title = it }
    }

    private fun deserializeIntent() {
        val json = intent.getStringExtra(KEY_PHOTO)
        if (json.isNullOrEmpty())
            throw IllegalStateException("No photo model in intent")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.details_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_share -> {
                val sendIntent = Intent()
                sendIntent.action = Intent.ACTION_SEND
                sendIntent.putExtra(Intent.EXTRA_TEXT, "${getString(R.string.look_at)} ${selectedPhoto.urls.full}")
                sendIntent.type = "text/plain"
                startActivity(Intent.createChooser(sendIntent, resources.getText(R.string.send_to)))
            }
            R.id.action_download -> prepareDownloadFile()

            android.R.id.home -> onBackPressed()
        }

        return true
    }

    private fun prepareDownloadFile() {
        if (Build.VERSION.SDK_INT >= 23) {
            checkStoragePermissions()
        } else
            getViewModel().requestLink(selectedPhoto.id)

    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            PERNISSION_RQT_CODE -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getViewModel().requestLink(selectedPhoto.id)
                } else {
                    Toast.makeText(this, R.string.required_permissions, Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }

    override fun startDownloading(url: String) {
        val intent = DownloadService.getIntent(applicationContext, url)
        startService(intent)
    }

    private fun checkStoragePermissions() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // No explanation needed, we can request the permission.

            ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    PERNISSION_RQT_CODE)

            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
        } else getViewModel().requestLink(selectedPhoto.id)
    }

}
