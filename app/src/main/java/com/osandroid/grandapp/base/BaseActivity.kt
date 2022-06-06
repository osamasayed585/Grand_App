package com.osandroid.grandapp.base

import android.content.Context
import android.net.ConnectivityManager
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.github.ybq.android.spinkit.SpinKitView
import com.osandroid.grandapp.databinding.ActivityBaseBinding
import com.osandroid.grandapp.dialogs.MessageDialog
import dagger.hilt.android.AndroidEntryPoint
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

    fun isNetworkConnected(): Boolean{
        val cm: ConnectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
    }

}