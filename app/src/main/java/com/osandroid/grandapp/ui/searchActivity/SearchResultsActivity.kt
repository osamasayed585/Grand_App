package com.osandroid.grandapp.ui.searchActivity

import android.app.SearchManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.osandroid.grandapp.R
import com.osandroid.grandapp.adapters.PhotosAdapter
import com.osandroid.grandapp.base.BaseActivity
import com.osandroid.grandapp.databinding.ActivitySearchResultsBinding
import com.osandroid.grandapp.roomDatabase.model.Photos
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchResultsActivity : BaseActivity() {
    lateinit var mBinding: ActivitySearchResultsBinding
    lateinit var mViewModel: SearchViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivitySearchResultsBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mViewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        mViewModel.onTellUserAnyThings.observe(this) {
            mBinding.notFound.isVisible = it.isNotEmpty()
            mBinding.notFound.text = it

        }

        val photoAdapter = PhotosAdapter()
        mBinding.listPhotos.adapter = photoAdapter

        mViewModel.photosResponseMutableLiveData.observe(this) { photos: List<Photos> ->
            run {

                photoAdapter.setData(photos)
            }
        }

        handleIntent(intent)
    }

    override fun onNewIntent(intent: Intent?) {
        handleIntent(intent!!)
        super.onNewIntent(intent)

    }

    private fun handleIntent(intent: Intent) {

        if (Intent.ACTION_SEARCH == intent.action) {
            val query: String? = intent.getStringExtra(SearchManager.QUERY)
            //use the query to search your data somehow
            mViewModel.searchPhotos(query!!)
        }
    }
}