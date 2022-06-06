package com.osandroid.grandapp.dialogs

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import com.osandroid.grandapp.R
import com.osandroid.grandapp.databinding.DialogMessageBinding
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class MessageDialog @Inject constructor(@ActivityContext context: Context) : Dialog(context) {

    private var mBinding: DialogMessageBinding
    private lateinit var mRetryCallApiListener: RetryCallApiListener
    private lateinit var mStayOfflineListener: StayOfflineListener

    init {
        setCancelable(false)
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        mBinding = DialogMessageBinding.inflate(LayoutInflater.from(context))
        setContentView(mBinding.root)

        window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        window!!.attributes.windowAnimations = R.style.Animation_Design_BottomSheetDialog

        mBinding.stayOffline.setOnClickListener { stayOffline() }
        mBinding.retry.setOnClickListener { retry() }

    }

    private fun retry() {
        dismiss()
        mRetryCallApiListener.retryCallApi()
    }

    private fun stayOffline() {
        dismiss()
        mStayOfflineListener.offline()
    }


    fun initStayOfflineClick(action: StayOfflineListener) {
        mStayOfflineListener = action
    }

    fun initRetryCallApiLineClick(action: RetryCallApiListener) {
        mRetryCallApiListener = action
    }

    fun setMessage(message: String) {
        mBinding.message.text = message
    }

    fun showDialog() {
        show()
    }


    interface StayOfflineListener {
        fun offline()
    }

    interface RetryCallApiListener {
        fun retryCallApi()
    }

}