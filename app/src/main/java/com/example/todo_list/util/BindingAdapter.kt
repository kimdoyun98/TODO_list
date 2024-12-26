package com.example.todo_list.util

import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.todo_list.data.room.RoutineEntity
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("d-day")
    fun setDDayText(view: TextView, deadline: String){
        val calendar: Calendar = Calendar.getInstance()
        val selectionFormatter = DateTimeFormatter.ofPattern("yyyyMMdd")

        val todayDate = calendar.time.time
        val endDate = SimpleDateFormat("yyyyMMdd").parse(deadline).time
        val today = selectionFormatter.format(LocalDate.now())

        view.text =
            if (today == deadline) "D-Day"
            else if (today.toInt() > deadline.toInt()) "기간 지남"
            else "D-${(endDate - todayDate) / (24 * 60 * 60 * 1000) + 1}"
    }

    @JvmStatic
    @BindingAdapter("entity", "days_text", "daily_text", requireAll = true)
    fun setTextColorWithDays(
        view: TextView,
        routineEntity: RoutineEntity,
        days: String,
        daily: String
    ) {
        val spannableString = SpannableString(days)

        val checkedDayIndex = ArrayList<Int>()
        for (i in 0..6) {
            if (routineEntity.day?.get(i)!!) checkedDayIndex.add(i)
        }

        if (checkedDayIndex.size == 7) {
            view.text = daily
            view.setTextColor(Color.BLUE)
        } else {
            for (i in checkedDayIndex) {
                val index = when (i) {
                    0 -> 14
                    1 -> 2
                    2 -> 4
                    3 -> 6
                    4 -> 8
                    5 -> 10
                    else -> 12
                }

                spannableString.setSpan(
                    ForegroundColorSpan(Color.BLUE),
                    index - 1,
                    index,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                view.text = spannableString
            }
        }
    }

}
