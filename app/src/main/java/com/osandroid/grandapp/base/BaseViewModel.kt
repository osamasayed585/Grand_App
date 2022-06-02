package com.osandroid.grandapp.base

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


    fun isInternetAvailable(objects: Objects): Boolean {
        return true;
    }
}