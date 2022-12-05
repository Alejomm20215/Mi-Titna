package com.example.mititna

import android.app.Application
import com.example.mititna.data.Repository
import com.example.mititna.helper.ServiceLocator
import com.example.mititna.helper.TimeUtils
import java.util.*


class App : Application() {

    // Depends on the flavor,
    val repository: Repository
        get() = ServiceLocator.provideRepository(this, Modules.MODULE_1.module)



    private fun evaluateStreak() {
        val currentDate: Date = TimeUtils.getCurrentDate()
        val lastPracticeDate: Date = repository.getPracticeDate() ?: return

        val differenceOfDays: Int = TimeUtils.subtractDates(currentDate, lastPracticeDate)

        if (differenceOfDays > 1) {
            // user is not continuous, reset the streak
            repository.resetStreak()
        }
    }
}