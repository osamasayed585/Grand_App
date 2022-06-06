package com.osandroid.grandapp.dI

import android.app.Dialog
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext

@Module
@InstallIn(ActivityComponent::class)
class ActivityModule {

    @Provides
    fun providerDialog(@ActivityContext context: Context): Dialog{
        return Dialog(context)
    }

}