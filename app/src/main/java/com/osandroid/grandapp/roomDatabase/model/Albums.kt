package com.osandroid.grandapp.roomDatabase.model

import javax.inject.Inject

data class Albums @Inject constructor(
    var userId: Int,
    var id: Int,
    var title: String
)