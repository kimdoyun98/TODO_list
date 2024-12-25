package com.example.todo_list.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import java.util.Calendar
import java.util.Date

class Alarm(private val context: Context) {
    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager

    fun setAlarm(hour : Int, minute : Int, alarm_code : Int, content : String, checkedDayList:MutableList<Boolean>){
        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra("alarm_rqCode", alarm_code)
            putExtra("content", content)
            putExtra("checkedDayList", checkedDayList.toBooleanArray())
            putExtra("hour", hour)
            putExtra("minute", minute)
        }

        val pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
            PendingIntent.getBroadcast(context,alarm_code,intent,PendingIntent.FLAG_IMMUTABLE)
        }else{
            PendingIntent.getBroadcast(context,alarm_code,intent,PendingIntent.FLAG_UPDATE_CURRENT)
        }

        val calendar : Calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
        }

        /**
         * Date 보다 이전 시간으로 등록 시 등록 즉시 알림 발생
         */
        if (calendar.time < Date()) {
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            alarmManager?.setExactAndAllowWhileIdle(
//                AlarmManager.RTC_WAKEUP,
//                calendar.timeInMillis,
//                pendingIntent
//            )
            val alarmClock = AlarmManager.AlarmClockInfo(calendar.timeInMillis, pendingIntent)
            alarmManager?.setAlarmClock(alarmClock, pendingIntent)
        }

    }

    fun cancelAlarm(alarm_code : Int){
        val intent = Intent(context, AlarmReceiver::class.java)
        val pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
            PendingIntent.getBroadcast(context,alarm_code,intent,PendingIntent.FLAG_IMMUTABLE)
        }else{
            PendingIntent.getBroadcast(context,alarm_code,intent,PendingIntent.FLAG_UPDATE_CURRENT)
        }
        alarmManager?.cancel(pendingIntent)
    }

}