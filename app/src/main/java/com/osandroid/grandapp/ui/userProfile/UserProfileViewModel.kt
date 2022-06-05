package com.osandroid.grandapp.ui.userProfile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.osandroid.grandapp.base.BaseViewModel
import com.osandroid.grandapp.roomDatabase.model.Albums
import com.osandroid.grandapp.roomDatabase.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor() : BaseViewModel() {

    @Inject
    lateinit var userProfileResponseMutableLiveData: MutableLiveData<List<User>>

    @Inject
    lateinit var albumsResponseMutableLiveData: MutableLiveData<List<Albums>>


    fun requestUser(id: Int) = viewModelScope.launch {

        onLoadingProgressBar?.postValue(true)

        val result: Response<List<User>> = grandRepository.requestUser(id)

        if (result.isSuccessful && result.body() != null) {
            onLoadingProgressBar?.postValue(false)
            userProfileResponseMutableLiveData.postValue(result.body())
        } else {
            onLoadingProgressBar?.postValue(false)
            Timber.i("Osama requestUser: ${result.message()}")
        }
    }

    fun requestAlbums(id: Int) = viewModelScope.launch {

        onLoadingProgressBar?.postValue(true)

        val result: Response<List<Albums>> = grandRepository.requestAlbums(id)

        if (result.isSuccessful && result.body() != null) {
            onLoadingProgressBar?.postValue(false)
            albumsResponseMutableLiveData.postValue(result.body())

        } else {
            onLoadingProgressBar?.postValue(false)
            Timber.i("Osama requestAlbums: ${result.message()}")
        }
    }
}