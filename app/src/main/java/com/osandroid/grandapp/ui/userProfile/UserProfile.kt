package com.osandroid.grandapp.ui.userProfile

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.osandroid.grandapp.adapters.UserAlbumsAdapter
import com.osandroid.grandapp.base.BaseActivity
import com.osandroid.grandapp.databinding.ActivityUserProfileBinding
import com.osandroid.grandapp.dialogs.MessageDialog
import com.osandroid.grandapp.roomDatabase.model.User
import com.osandroid.grandapp.ui.albumDetails.AlbumDetails
import com.osandroid.grandapp.utils.CONSTANTS
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

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
        mViewModel.onLoadingProgressBar.observe(this) { onLoading(it) }


        mViewModel.onApiError.observe(this) { message ->
            isInternetAvailable(message)
            mMessageDialog.showDialog()
        }

        mMessageDialog.initStayOfflineClick(object : MessageDialog.StayOfflineListener {
            override fun offline() {
                Toast.makeText(applicationContext, "You are offline", Toast.LENGTH_LONG).show()
                mViewModel.fetchUser()
                mViewModel.fetchAlbums()
            }
        })
        mMessageDialog.initRetryCallApiLineClick(object : MessageDialog.RetryCallApiListener {
            override fun retryCallApi() {
                Toast.makeText(applicationContext, "Retry connect to internet", Toast.LENGTH_LONG)
                    .show()
                if (isNetworkConnected())
                    requestApis()
            }
        })
        if (isNetworkConnected())
            requestApis()

        mAlbumsAdapter = UserAlbumsAdapter()
        mBinding.userAlbums.adapter = mAlbumsAdapter

        mAlbumsAdapter.initListener(onClickAlbumListener())

        mViewModel.userProfileResponse.observe(this) { user: List<User> ->
            mViewModel.addUser(user)
            mBinding.userName.text = user[0].name
            mBinding.userAddress.text = user[0].name
        }

        mViewModel.albumsResponse.observe(this) {
            mViewModel.addAlbums(it)
            mAlbumsAdapter.setData(it)
        }
    }

    private fun requestApis() {
        val randomNum = (1..10).random()
        Timber.i("this is a random number $randomNum")
        mViewModel.requestUser(randomNum)
        mViewModel.requestAlbums(randomNum)
    }

    private fun onClickAlbumListener() = object : UserAlbumsAdapter.OnClickAlbumListener {
        override fun onAlbumClicked(id: Int) {
            val intent = Intent(this@UserProfile, AlbumDetails::class.java).apply {
                putExtra(CONSTANTS.INTENT.ID, id)
            }
            startActivity(intent)
        }
    }

    override fun onStart() {
        if (isNetworkConnected())
            requestApis()
        super.onStart()
    }
}