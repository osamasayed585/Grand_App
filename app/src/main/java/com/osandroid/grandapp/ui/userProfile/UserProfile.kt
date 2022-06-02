package com.osandroid.grandapp.ui.userProfile

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.osandroid.grandapp.base.BaseActivity
import com.osandroid.grandapp.databinding.ActivityUserProfileBinding
import com.osandroid.grandapp.roomDatabase.model.Albums
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserProfile : BaseActivity() {

    private lateinit var mBinding: ActivityUserProfileBinding
    private lateinit var mViewModel: UserProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mViewModel = ViewModelProvider(this).get(UserProfileViewModel::class.java)

        mViewModel.requestUser(1)

        mViewModel.requestAlbums(1)

        mViewModel.userProfileResponseMutableLiveData.observe(this, { user -> run {
                mBinding.userName.text = user[0].name
            }})

        mViewModel.albumsResponseMutableLiveData.observe(this, { albums: List<Albums> -> run {
            mBinding.userPhoto.text = albums[0].title
        }})

    }

}