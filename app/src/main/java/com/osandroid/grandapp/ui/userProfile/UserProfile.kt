package com.osandroid.grandapp.ui.userProfile

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.osandroid.grandapp.adapters.UserAlbumsAdapter
import com.osandroid.grandapp.base.BaseActivity
import com.osandroid.grandapp.databinding.ActivityUserProfileBinding
import com.osandroid.grandapp.ui.albumDetails.AlbumDetails
import com.osandroid.grandapp.utils.CONSTANTS
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserProfile : BaseActivity() {

    private lateinit var mBinding: ActivityUserProfileBinding
    private lateinit var mViewModel: UserProfileViewModel
    private lateinit var mAlbumsAdapter: UserAlbumsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mViewModel = ViewModelProvider(this)[UserProfileViewModel::class.java]

        mViewModel.requestUser(1)
        mViewModel.requestAlbums(1)

        mAlbumsAdapter = UserAlbumsAdapter()
        mBinding.userAlbums.adapter = mAlbumsAdapter



        mAlbumsAdapter.initListener(object : UserAlbumsAdapter.OnClickAlbumListener {
            override fun onAlbumClicked(id: Int) {
                val intent = Intent(this@UserProfile, AlbumDetails::class.java).apply {
                    putExtra(CONSTANTS.INTENT.ID, id)
                }
                startActivity(intent)
            }
        })

        mViewModel.userProfileResponseMutableLiveData.observe(this, { user ->
            run {
                mBinding.userName.text = user[0].name
                mBinding.userAddress.text = user[0].name
            }
        })
        mViewModel.albumsResponseMutableLiveData.observe(this) { mAlbumsAdapter.setData(it) }
    }

}