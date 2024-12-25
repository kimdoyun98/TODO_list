package com.example.todo_list.alarm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.todo_list.R
import com.example.todo_list.alarm.Alarm.Companion.ALARM_REQUEST_CODE
import com.example.todo_list.alarm.Alarm.Companion.CHECKED_DAY_LIST
import com.example.todo_list.alarm.Alarm.Companion.CONTENT
import com.example.todo_list.alarm.Alarm.Companion.HOUR
import com.example.todo_list.alarm.Alarm.Companion.MINUTE
import com.example.todo_list.util.MyApplication
import com.example.todo_list.util.PreferenceUtil.Companion.PUSH_ALARM
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Thread.sleep
import java.util.Calendar
import javax.inject.Inject

/**
 * 알림 기능
 */
@AndroidEntryPoint
class AlarmReceiver : BroadcastReceiver() {
    companion object {
        private const val CHANNEL_ID = "TodayAlarm"
        private const val CHANNEL_NAME = "Alarm"
    }
    @Inject lateinit var alarm: Alarm

    override fun onReceive(context: Context?, intent: Intent?) {
        val today = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
        val checkedDayList = intent?.extras!!.getBooleanArray(CHECKED_DAY_LIST)

        if (!checkedDayList!![today - 1] || !MyApplication.prefs.getAlarm(PUSH_ALARM)) return

        val notificationManager: NotificationManager =
            context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            notificationManager.createNotificationChannel(
                NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance)
            )
        }

        val intent2 = Intent(context, AlarmService::class.java)
        val requestCode = intent.extras!!.getInt(ALARM_REQUEST_CODE)
        val content = intent.extras!!.getString(CONTENT)

        val pendingIntent =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
                PendingIntent.getActivity(
                    context,
                    requestCode,
                    intent2,
                    PendingIntent.FLAG_IMMUTABLE
                )
            else PendingIntent.getActivity(
                context,
                requestCode,
                intent2,
                PendingIntent.FLAG_UPDATE_CURRENT
            );


        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.mipmap.todo_icon)
            .setContentTitle("정각에 해야지")
            .setContentText(content)
            .setAutoCancel(false)
            .setShowWhen(true)
            .setColor(ContextCompat.getColor(context, R.color.purple_200))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .build()

        notificationManager.notify(1, builder)

        //알림 재설정
        val hour = intent.extras!!.getInt(HOUR)
        val minute = intent.extras!!.getInt(MINUTE)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                sleep(60000)
                alarm.setAlarm(
                    hour,
                    minute,
                    requestCode,
                    content!!,
                    checkedDayList.toMutableList()
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
