package com.example.viewpager2testapplication

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import timber.log.Timber

class BaseClass : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
        Timber.plant(Timber.DebugTree());

    }
}