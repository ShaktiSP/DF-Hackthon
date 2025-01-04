package com.example.dfhackthon.utils

import com.example.dfhackthon.model.CalendarDate
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.regex.Pattern

fun isValidEmailId(email: String): Boolean {
    return Pattern.compile(
        "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
    ).matcher(email).matches()
}

fun generateCalendarDays(): List<CalendarDate> {
    val calendar = Calendar.getInstance()
    val days = mutableListOf<CalendarDate>()

    val dayFormat = SimpleDateFormat("EEE", Locale.getDefault())
    val dateFormat = SimpleDateFormat("dd", Locale.getDefault())
    val monthFormat = SimpleDateFormat("MMM", Locale.getDefault())

    for (i in 0 until 7) {
        val dayOfWeek = dayFormat.format(calendar.time)
        val date = dateFormat.format(calendar.time)
        val month = monthFormat.format(calendar.time)
        days.add(CalendarDate(dayOfWeek, date, month))
        calendar.add(Calendar.DAY_OF_MONTH, 1)
    }
    return days
}

fun formatDate(inputDate: String): String {
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        val outputFormat = SimpleDateFormat("EEE dd MMM", Locale.ENGLISH)
        val date = inputFormat.parse(inputDate)
        outputFormat.format(date ?: Date())
    } catch (e: Exception) {
        "Invalid date"
    }
}

