package com.osandroid.grandapp.ui.albumDetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.osandroid.grandapp.R
import com.osandroid.grandapp.base.BaseActivity
import com.osandroid.grandapp.databinding.ActivityAlbumDetalisBinding
import com.osandroid.grandapp.roomDatabase.model.Photos
import com.osandroid.grandapp.utils.CONSTANTS
import dagger.hilt.android.AndroidEntryPoint

const val DID_NOT_COME = -1

@AndroidEntryPoint
class AlbumDetails : BaseActivity() {

    lateinit var mBinding: ActivityAlbumDetalisBinding
    lateinit var mViewModel: AlbumDetailsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityAlbumDetalisBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        mViewModel = ViewModelProvider(this)[AlbumDetailsViewModel::class.java]


        val albumId = intent.getIntExtra(CONSTANTS.INTENT.ID, DID_NOT_COME)

        mViewModel.requestPhotos(albumId)

        mViewModel.photosResponseMutableLiveData.observe(this, { photos: List<Photos> ->
            run {
                mBinding.userId.text = photos[0].title
            }
        })


    }
}