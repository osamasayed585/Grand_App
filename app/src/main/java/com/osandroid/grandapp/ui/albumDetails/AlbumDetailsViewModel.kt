package com.osandroid.grandapp.ui.albumDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.osandroid.grandapp.base.BaseViewModel
import com.osandroid.grandapp.roomDatabase.model.Photos
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AlbumDetailsViewModel @Inject constructor() : BaseViewModel() {

    @Inject
    lateinit var photosResponseMutableLiveData: MutableLiveData<List<Photos>>

    fun requestPhotos(id: Int) = viewModelScope.launch {

        onLoadingProgressBar.postValue(true)

        val result: Response<List<Photos>> = grandRepository.requestPhotos(id)

        if (result.isSuccessful && result.body() != null) {
            onLoadingProgressBar.postValue(false)
            photosResponseMutableLiveData.postValue(result.body())

        } else {
            onLoadingProgressBar.postValue(false)
            Timber.i("Osama requestPhotos: ${result.message()}")
        }
    }
}