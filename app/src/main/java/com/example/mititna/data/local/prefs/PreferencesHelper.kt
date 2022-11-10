package com.example.mititna.data.local.prefs

import java.util.*

interface PreferencesHelper {

    fun addXp(value: Int)

    fun getXp(): Int

    fun updatePracticeDate(date: Date)

    fun getPracticeDate(): Date?

    fun incrementStreak()

    fun resetStreak()

    fun getStreak(): Int
}