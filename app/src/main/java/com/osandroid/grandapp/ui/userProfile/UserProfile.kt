package com.osandroid.grandapp.ui.userProfile

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.osandroid.grandapp.R
import com.osandroid.grandapp.adapters.UserAlbumsAdapter
import com.osandroid.grandapp.base.BaseActivity
import com.osandroid.grandapp.databinding.ActivityUserProfileBinding
import com.osandroid.grandapp.dialogs.MessageDialog
import com.osandroid.grandapp.roomDatabase.model.User
import com.osandroid.grandapp.ui.albumDetails.AlbumDetails
import com.osandroid.grandapp.utils.CONSTANTS
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.dialog_message.*
import timber.log.Timber
import kotlin.random.Random

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
        mViewModel.onLoadingProgressBar.observe(this) {onLoading(it)}


        mViewModel.onApiError.observe(this) { message ->
            isInternetAvailable(message)
            mMessageDialog.showDialog()
        }

        mMessageDialog.initStayOfflineClick(object : MessageDialog.StayOfflineListener {
            override fun offline() {
                Toast.makeText(applicationContext, "You are offline", Toast.LENGTH_LONG).show()
            }
        })
        mMessageDialog.initRetryCallApiLineClick(object : MessageDialog.RetryCallApiListener {
            override fun retryCallApi() {
                Toast.makeText(applicationContext, "Retry connect to internet", Toast.LENGTH_LONG).show()
                requestApis()
            }
        })

        requestApis()

        mAlbumsAdapter = UserAlbumsAdapter()
        mBinding.userAlbums.adapter = mAlbumsAdapter

        mAlbumsAdapter.initListener(onClickAlbumListener())

        mViewModel.userProfileResponseMutableLiveData.observe(this) { user: List<User> ->
            mBinding.userName.text = user[0].name
            mBinding.userAddress.text = user[0].name
        }

        mViewModel.albumsResponseMutableLiveData.observe(this) { mAlbumsAdapter.setData(it) }
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
        requestApis()
        super.onStart()
    }
}