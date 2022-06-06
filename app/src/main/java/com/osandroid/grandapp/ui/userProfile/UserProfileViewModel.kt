package com.osandroid.grandapp.ui.userProfile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.osandroid.grandapp.base.BaseViewModel
import com.osandroid.grandapp.roomDatabase.model.Albums
import com.osandroid.grandapp.roomDatabase.model.User
import com.osandroid.grandapp.utils.Result
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

        onLoadingProgressBar.postValue(true)

        val result = try {
            Result.Success(grandRepository.requestUser(id))
        } catch (e: Exception) {
            Result.Error(Exception("Network request failed"))
        }
        when (result) {
            is Result.Success<Response<List<User>>> -> {
                onLoadingProgressBar.postValue(false)
                userProfileResponseMutableLiveData.postValue(result.data.body())
            }
            else -> {
                onLoadingProgressBar.postValue(false)
                onApiError.postValue("no internet")
                Timber.i("Osama requestUser: Error")
            }
        }

    }

    fun requestAlbums(id: Int) = viewModelScope.launch {
        onLoadingProgressBar.postValue(true)

        val result = try {
            Result.Success(grandRepository.requestAlbums(id))
        } catch (e: Exception) {
            Result.Error(Exception("Network request failed"))
        }

        when (result) {
            is Result.Success<Response<List<Albums>>> -> {
                onLoadingProgressBar.postValue(false)
                albumsResponseMutableLiveData.postValue(result.data.body())
            }
            else -> {
                onLoadingProgressBar.postValue(false)
                onApiError.postValue("no internet")
                Timber.i("Osama requestPhotos: Error")
            }
        }
    }
}