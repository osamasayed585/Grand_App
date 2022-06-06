package com.osandroid.grandapp.ui.albumDetails

import android.net.Network
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.osandroid.grandapp.base.BaseViewModel
import com.osandroid.grandapp.roomDatabase.model.Photos
import com.osandroid.grandapp.utils.Result
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

        val result = try {
            Result.Success(grandRepository.requestPhotos(id))
        } catch (e: Exception) {
            Result.Error(Exception("No Internet"))
        }

        when (result) {
            is Result.Success<Response<List<Photos>>> -> {
                onLoadingProgressBar.postValue(false)
                photosResponseMutableLiveData.postValue(result.data.body())
            }
            else -> {
                onLoadingProgressBar.postValue(false)
                onApiError.postValue("No Internet")
                Timber.i("Osama requestPhotos")
            }
        }
    }

    fun addPhotos(photos: List<Photos>) = viewModelScope.launch {
        (grandRepository.addPhotos(photos))
    }

    fun fetchAllPhotos() = viewModelScope.launch {
        val result = grandRepository.fetchAllPhotosData()
        photosResponseMutableLiveData.postValue(result)
    }
}