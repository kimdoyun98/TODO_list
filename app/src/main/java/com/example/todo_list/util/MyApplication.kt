package com.example.todo_list.util

import android.app.Application
import android.util.Log
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import com.example.todo_list.worker.ResetRoutineWorker
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MyApplication : Application(), Configuration.Provider {
    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .setMinimumLoggingLevel(Log.DEBUG)
            .build()

    override fun onCreate() {
        super.onCreate()
        prefs = PreferenceUtil(applicationContext)
        instance = this

        Log.e("WorkerState", prefs.getWorkerState().toString())
        Log.e("WorkerLog", prefs.getWorkerLog().toString())
        if(!prefs.getWorkerState()){
            ResetRoutineWorker.runReset(this)
            prefs.setWorkerState(true)
        }
    }

    companion object {
        lateinit var instance: MyApplication
        lateinit var prefs: PreferenceUtil
    }
}
