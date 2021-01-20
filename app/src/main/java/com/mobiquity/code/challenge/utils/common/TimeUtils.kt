package com.mobiquity.code.challenge.utils.common

import java.util.*

object TimeUtils {

    private const val SECOND_MILLIS = 1000
    private const val MINUTE_MILLIS = 60 * SECOND_MILLIS
    private const val HOUR_MILLIS = 60 * MINUTE_MILLIS
    private const val DAY_MILLIS = 24 * HOUR_MILLIS

    fun getTimeAgo(date: Date): String {
        val time = date.time
        val now = System.currentTimeMillis()
        if (time > now || time <= 0) return ""

        val diff = now - time
        return if (diff < MINUTE_MILLIS) {
            "just now"
        } else if (diff < 2 * MINUTE_MILLIS) {
            "a minute ago"
        } else if (diff < 50 * MINUTE_MILLIS) {
            "${diff / MINUTE_MILLIS} minutes ago"
        } else if (diff < 90 * MINUTE_MILLIS) {
            "an hour ago"
        } else if (diff < 24 * HOUR_MILLIS) {
            "${diff / HOUR_MILLIS} hours ago"
        } else if (diff < 48 * HOUR_MILLIS) {
            "yesterday"
        } else {
            "${diff / DAY_MILLIS} days ago"
        }
    }

    fun getStartOfDayTimestamp(calendar: Calendar): Long {
        val newCalendar = Calendar.getInstance(TimeZone.getDefault())
        newCalendar.timeInMillis = calendar.timeInMillis
        newCalendar[Calendar.HOUR_OF_DAY] = 0
        newCalendar[Calendar.MINUTE] = 0
        newCalendar[Calendar.SECOND] = 0
        newCalendar[Calendar.MILLISECOND] = 0
        return newCalendar.timeInMillis
    }

    /**
     * Get timestamp of end of day 23:59:59
     *
     * @param calendar instance of [Calendar]
     * @return timestamp
     */
    fun getEndOfDayTimestamp(calendar: Calendar): Long {
        val newCalendar = Calendar.getInstance(TimeZone.getDefault())
        newCalendar.timeInMillis = calendar.timeInMillis
        newCalendar[Calendar.HOUR_OF_DAY] = 23
        newCalendar[Calendar.MINUTE] = 59
        newCalendar[Calendar.SECOND] = 59
        newCalendar[Calendar.MILLISECOND] = 0
        return newCalendar.timeInMillis
    }

    /**
     * Add days to calendar and return result
     *
     * @param cal  instance of [Calendar]
     * @param days number of days
     * @return instance of [Calendar]
     */
    fun addDays(cal: Calendar, days: Int): Calendar {
        val calendar = Calendar.getInstance(TimeZone.getDefault())
        calendar.timeInMillis = cal.timeInMillis
        calendar.add(Calendar.DATE, days)
        return calendar
    }
}