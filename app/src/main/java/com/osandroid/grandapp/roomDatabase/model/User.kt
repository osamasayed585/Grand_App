package com.osandroid.grandapp.roomDatabase.model

import javax.inject.Inject

data class User(
    var id: Int,
    var name: String,
    var username: String,
    var email: String,
    var address: Address,
    var phone: String,
    var website: String,
    var company: Company,
)