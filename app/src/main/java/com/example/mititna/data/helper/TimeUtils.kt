package com.example.mititna.data.helper

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

object TimeUtils {

    private val dateFormat = SimpleDateFormat("dd-MMM-yyyy", Locale.US)

    fun convertDateToString(date: Date): String = dateFormat.format(date)

    fun convertStringToDate(string: String?): Date? {
        if (string == null) return null

        return try {
            dateFormat.parse(string)
        } catch (e: ParseException) {
            e.printStackTrace()
            null
        }
    }

    fun getCurrentDate(): Date = Calendar.getInstance().time

    fun getCurrentDayOfWeek(): Int = (Calendar.getInstance().get(Calendar.DAY_OF_WEEK))

    fun subtractDates(currentDate: Date, lastPracticeDate: Date): Int {
        return try {
            val currentDateWithoutTime =
                dateFormat.parse(dateFormat.format(currentDate)) ?: return 0
            val lastPracticeDateWithoutTime =
                dateFormat.parse(dateFormat.format(lastPracticeDate)) ?: return 0

            val diff = currentDateWithoutTime.time - lastPracticeDateWithoutTime.time

            TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS).toInt()

        } catch (e: ParseException) {
            e.printStackTrace()
            0
        }
    }
}