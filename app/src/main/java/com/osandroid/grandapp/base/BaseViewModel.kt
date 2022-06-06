package com.osandroid.grandapp.base

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.osandroid.grandapp.repository.GrandRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.internal.disposables.ArrayCompositeDisposable
import java.util.*
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var grandRepository: GrandRepository

    @Inject
    lateinit var onLoadingProgressBar: MutableLiveData<Boolean>

    @Inject
    lateinit var onApiError: MutableLiveData<String>
}