package com.osandroid.grandapp.ui.albumDetails


import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import com.osandroid.grandapp.R
import com.osandroid.grandapp.adapters.PhotosAdapter
import com.osandroid.grandapp.base.BaseActivity
import com.osandroid.grandapp.databinding.ActivityAlbumDetalisBinding
import com.osandroid.grandapp.dialogs.MessageDialog
import com.osandroid.grandapp.roomDatabase.model.Photos
import com.osandroid.grandapp.utils.CONSTANTS
import dagger.hilt.android.AndroidEntryPoint

const val DID_NOT_COME = -1

@AndroidEntryPoint
class AlbumDetails : BaseActivity() {

    lateinit var mBinding: ActivityAlbumDetalisBinding
    lateinit var mViewModel: AlbumDetailsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityAlbumDetalisBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        setSupportActionBar(mBinding.myToolbar)

        mViewModel = ViewModelProvider(this)[AlbumDetailsViewModel::class.java]
        mViewModel.onLoadingProgressBar.observe(this) { onLoading(it) }
        mViewModel.onApiError.observe(this) { message ->
            isInternetAvailable(message)
            mMessageDialog.showDialog()
        }

        mMessageDialog.initStayOfflineClick(object : MessageDialog.StayOfflineListener {
            override fun offline() {
                Toast.makeText(applicationContext, "You are offline", Toast.LENGTH_LONG).show()
                mViewModel.fetchAllPhotos()
            }
        })
        mMessageDialog.initRetryCallApiLineClick(object : MessageDialog.RetryCallApiListener {
            override fun retryCallApi() {
                Toast.makeText(applicationContext, "Retry connect to internet", Toast.LENGTH_LONG)
                    .show()
                hitApi()
            }
        })

        if (isNetworkConnected())
            hitApi()

        val photoAdapter = PhotosAdapter()
        mBinding.photos.adapter = photoAdapter

        mViewModel.photosResponseMutableLiveData.observe(this) { photos: List<Photos> ->
            run {
                mViewModel.addPhotos(photos)
                photoAdapter.setData(photos)
            }
        }

        mBinding.myToolbar.setNavigationOnClickListener {
            // Handle navigation icon press
        }

        mBinding.myToolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.search -> {
                    // Handle edit text press
                    Toast.makeText(applicationContext, "hello search", Toast.LENGTH_LONG)
                    true
                }
                else -> false
            }
        }
    }

    private fun hitApi() {
        val albumId = intent.getIntExtra(CONSTANTS.INTENT.ID, DID_NOT_COME)

        mViewModel.requestPhotos(albumId)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.album_details_menu, menu)

        val searchItem: MenuItem? = menu?.findItem(R.id.search)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView: SearchView = searchItem?.actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        return true
    }

    override fun onStart() {
        super.onStart()
        hitApi()
    }

}