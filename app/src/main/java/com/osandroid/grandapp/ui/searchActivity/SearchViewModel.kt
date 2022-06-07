package com.osandroid.grandapp.ui.searchActivity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.osandroid.grandapp.base.BaseViewModel
import com.osandroid.grandapp.roomDatabase.model.Photos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor() : BaseViewModel() {

    @Inject
    lateinit var photosResponseMutableLiveData: MutableLiveData<List<Photos>>


    fun searchPhotos(title: String) = viewModelScope.launch {

        onLoadingProgressBar.postValue(true)

        val result = grandRepository.search(title)

        if (result.isNotEmpty()) {
            onLoadingProgressBar.postValue(false)
            photosResponseMutableLiveData.postValue(result)
        } else {
            onLoadingProgressBar.postValue(false)
            onTellUserAnyThings.postValue("There are no $title.")
            Timber.i("Osama not data in photos")
        }
    }
}