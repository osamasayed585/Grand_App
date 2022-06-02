package com.osandroid.grandapp.dI


import androidx.lifecycle.MutableLiveData
import com.osandroid.grandapp.restfulApi.ApiService
import com.osandroid.grandapp.roomDatabase.model.Albums
import com.osandroid.grandapp.roomDatabase.model.User
import com.osandroid.grandapp.utils.CONSTANTS.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber


@Module
@InstallIn(ViewModelComponent::class)
class NetworkModule {

    @Provides
    @ViewModelScoped
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor =
            HttpLoggingInterceptor { message: String? -> Timber.i(message) }
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return httpLoggingInterceptor
    }

    @Provides
    @ViewModelScoped
    fun okHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }


    @Provides
    @ViewModelScoped
    fun getRetrofit(okHttpClient: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)
    }


    @Provides
    fun provideBooleanMutableLiveData(): MutableLiveData<Boolean> {
        return MutableLiveData()
    }

    @Provides
    fun provideAlbumsMutableLiveData(): MutableLiveData<List<Albums>> {
        return MutableLiveData<List<Albums>>()
    }

    @Provides
    fun userProfileResponseMutableLiveData(): MutableLiveData<List<User>> {
        return MutableLiveData()
    }
}