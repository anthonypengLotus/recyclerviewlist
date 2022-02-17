package com.example.myapplication

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.workmanager.TokenWorker
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        val request = PeriodicWorkRequest.Builder(TokenWorker::class.java, 30, TimeUnit.MINUTES).setInitialDelay(30, TimeUnit.MINUTES).build()
        WorkManager.getInstance(this).enqueue(request)
//        val decorView = window.decorView
//        val option = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
//        decorView.systemUiVisibility = option
//        window.statusBarColor = Color.TRANSPARENT
    }
}