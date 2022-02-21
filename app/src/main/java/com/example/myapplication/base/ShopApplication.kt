package com.example.myapplication.base

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ShopApplication : Application() {
    companion object {
        var _context: Application? = null
        fun getContext(): Context {
            return _context!!
        }

    }

    override fun onCreate() {
        super.onCreate()
        _context = this
    }
}
