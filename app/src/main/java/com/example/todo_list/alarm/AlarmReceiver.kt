package com.example.todo_list.alarm

import android.app.Notification
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Thread.sleep
import java.util.Calendar

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        val today = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
        val checkedDayList = intent?.extras!!.getBooleanArray(CHECKED_DAY_LIST)
        val requestCode = intent.extras!!.getInt(ALARM_REQUEST_CODE)
        val content = intent.extras!!.getString(CONTENT)

        if (!checkedDayList!![today - 1] || !MyApplication.prefs.getAlarm(PUSH_ALARM)) return

        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        createChannel(notificationManager)

        val pendingIntent = createPendingIntent(context, requestCode)
        val builder = createNotificationBuilder(context, content, pendingIntent)

        notificationManager.notify(1, builder)

        CoroutineScope(Dispatchers.IO).launch {
            resetAlarm(
                context,
                intent.extras!!.getInt(HOUR),
                intent.extras!!.getInt(MINUTE),
                requestCode,
                content!!,
                checkedDayList.toMutableList()
            )
        }
    }

    private fun createNotificationBuilder(
        context: Context,
        content: String?,
        pendingIntent: PendingIntent
    ): Notification {
        return NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.mipmap.todo_icon)
            .setContentTitle(context.getString(R.string.app_name))
            .setContentText(content)
            .setAutoCancel(false)
            .setShowWhen(true)
            .setColor(ContextCompat.getColor(context, R.color.purple_200))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .build()
    }

    private fun resetAlarm(
        context: Context,
        hour: Int,
        minute: Int,
        requestCode: Int,
        content: String,
        checkedDayList: MutableList<Boolean>
    ) {
        try {
            sleep(60000)
            Alarm(context).setAlarm(
                hour,
                minute,
                requestCode,
                content,
                checkedDayList.toMutableList()
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun createChannel(notificationManager: NotificationManager) {
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        notificationManager.createNotificationChannel(
            NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance)
        )
    }

    private fun createPendingIntent(context: Context, requestCode: Int): PendingIntent {
        val serviceIntent = Intent(context, AlarmService::class.java)

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
            PendingIntent.getActivity(
                context,
                requestCode,
                serviceIntent,
                PendingIntent.FLAG_IMMUTABLE
            )
        else PendingIntent.getActivity(
            context,
            requestCode,
            serviceIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
    }

    companion object {
        private const val CHANNEL_ID = "TodayAlarm"
        private const val CHANNEL_NAME = "Alarm"
    }
}
