package com.osandroid.grandapp.ui.albumDetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.osandroid.grandapp.R
import com.osandroid.grandapp.databinding.ActivityAlbumDetalisBinding
import com.osandroid.grandapp.utils.CONSTANTS

class AlbumDetails : AppCompatActivity() {

    lateinit var mBinding: ActivityAlbumDetalisBinding
    var albumId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityAlbumDetalisBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_album_detalis)

        albumId = intent.getStringExtra(CONSTANTS.INTENT.ID)

        mBinding.userId.text = albumId
    }
}