package com.example.todo_list.worker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.ExistingWorkPolicy
import androidx.work.ForegroundInfo
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.example.todo_list.R
import com.example.todo_list.data.repository.routine.RoutineRepository
import com.example.todo_list.util.MyApplication.Companion.prefs
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.coroutineScope
import java.time.Duration
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.temporal.ChronoUnit
import java.util.concurrent.TimeUnit

@HiltWorker
class ResetRoutineWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val routineRepository: RoutineRepository,
) : CoroutineWorker(appContext, workerParams) {

    companion object {
        private const val HOUR = 0
        private const val MINUTE = 0
        private const val WORKER_NAME = "RESET_ROUTINE_SUCCESS"
        private const val NOTIFICATION_ID = 101

        fun runReset(context: Context) {
            Log.d("ResetRoutineWorker", "runReset")
            val duration = getDurationTime()

            val workRequest = OneTimeWorkRequestBuilder<ResetRoutineWorker>()
                .setInitialDelay(duration.seconds, TimeUnit.SECONDS)
                .build()

            WorkManager.getInstance(context)
                .enqueueUniqueWork(
                    WORKER_NAME,
                    ExistingWorkPolicy.REPLACE,
                    workRequest
                )
        }

        private fun getDurationTime(): Duration {
            val triggerHour = HOUR
            val triggerMinute = MINUTE

            val newSyncTime = LocalTime.of(triggerHour, triggerMinute)
            val now: LocalDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES)
            val nowTime: LocalTime = now.toLocalTime()

            val plusDay = if (nowTime == newSyncTime || nowTime.isAfter(newSyncTime)) 1 else 0

            val nextTriggerTime = now.plusDays(plusDay.toLong())
                .withHour(newSyncTime.hour)
                .withMinute(newSyncTime.minute)

            return Duration.between(LocalDateTime.now(), nextTriggerTime)
        }
    }

    override suspend fun doWork(): Result = coroutineScope {
        try {
            setForeground(createForegroundInfo())
            routineRepository.resetSuccess()
            prefs.setWorkLog("Success")
            Result.success()
        } catch (e: Exception) {
            prefs.setWorkLog(e.message.toString())
            Result.failure()
        } finally {
            runReset(applicationContext)
        }
    }

    private fun createForegroundInfo(): ForegroundInfo {
        createChannel()
        val notification = NotificationCompat.Builder(applicationContext, WORKER_NAME)
            .setContentTitle(applicationContext.getString(R.string.app_name))
            .setTicker(applicationContext.getString(R.string.notification_reset_ticker)) //간단한 설명
            .setContentText(applicationContext.getString(R.string.notification_reset_content))
            .setSmallIcon(R.mipmap.todo_icon)
            .setOngoing(true)
            .setAutoCancel(false)
            .setShowWhen(true)
            .build()

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            ForegroundInfo(NOTIFICATION_ID, notification, FOREGROUND_SERVICE_TYPE_DATA_SYNC)
        } else {
            ForegroundInfo(NOTIFICATION_ID, notification)
        }
    }

    private fun createChannel() {
        val notificationManager: NotificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        notificationManager.createNotificationChannel(
            NotificationChannel(WORKER_NAME, WORKER_NAME, importance)
        )
    }
}
