package com.example.myapplication.workmanager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.myapplication.api.ShopService
import javax.inject.Inject

class TokenWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    @Inject
    lateinit var shopService: ShopService
    override fun doWork(): Result {

        return Result.success()
    }
}