package com.osandroid.grandapp.base

import android.content.Context
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.github.ybq.android.spinkit.SpinKitView
import com.google.android.material.snackbar.Snackbar
import com.osandroid.grandapp.R
import com.osandroid.grandapp.databinding.ActivityBaseBinding
import com.osandroid.grandapp.dialogs.MessageDialog
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ActivityContext
import kotlinx.android.synthetic.main.dialog_message.*
import javax.inject.Inject

@AndroidEntryPoint
open class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var mMessageDialog: MessageDialog

    lateinit var progressView: SpinKitView

    private var mBinding: ActivityBaseBinding? = null


    override fun setContentView(view: View?) {
        mBinding = ActivityBaseBinding.inflate(layoutInflater)
        progressView = mBinding!!.progressView
        mBinding!!.fContainer.addView(view)
        super.setContentView(mBinding!!.root)
    }

    fun onLoading(isLoading: Boolean): (v: View) -> Unit = {
        progressView.isVisible = isLoading
    }

    fun isInternetAvailable(message: String): (m: String) -> Unit = {
        mMessageDialog.setMessage(it)
    }

}