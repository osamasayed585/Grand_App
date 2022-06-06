package com.osandroid.grandapp.ui.searchActivity

import android.app.SearchManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.osandroid.grandapp.R
import com.osandroid.grandapp.base.BaseActivity
import com.osandroid.grandapp.databinding.ActivitySearchResultsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchResultsActivity : BaseActivity() {
    lateinit var mBinding: ActivitySearchResultsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivitySearchResultsBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        handleIntent(intent)
    }

    override fun onNewIntent(intent: Intent?) {
        handleIntent(intent!!)
        super.onNewIntent(intent)

    }

    private fun handleIntent(intent: Intent) {

        if (Intent.ACTION_SEARCH == intent.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            //use the query to search your data somehow
            Snackbar.make(mBinding.root, "this is $query", Toast.LENGTH_LONG).show()
        }
    }
}