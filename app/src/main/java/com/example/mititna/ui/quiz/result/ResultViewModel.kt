package com.example.mititna.ui.quiz.result

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mititna.data.Repository
import com.example.mititna.data.helper.TimeUtils
import com.example.mititna.ui.charts.Charts
import com.example.mititna.ui.quiz.QuizViewModel
import kotlinx.coroutines.launch

class ResultViewModel(
    repository: Repository,
    exerciseId: String?,
    val quizResult: QuizResult,
    lifeLeft: Int
) : ViewModel() {

    // SUNDAY = 1
    // MONDAY = 2
    // TUESDAY = 3
    // WEDNESDAY = 4
    // THURSDAY = 5
    // FRIDAY = 6
    // SATURDAY = 7

    enum class QuizResult {
        SUCCESS, // Quiz was completed, show streaks and scores
        FAILED // Quiz wasn't completed, show failed message
    }

    companion object {
        const val XP_PER_QUIZ = 10
        val DAYS_OF_WEEK = arrayOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")
    }

    val xp = XP_PER_QUIZ - (QuizViewModel.LIFELINE_PER_QUIZ - lifeLeft)
    val totalXP = repository.getXp() + if (quizResult == QuizResult.SUCCESS) xp else 0
    var streakCount: Int = -1
    val dayOfWeek:Int = TimeUtils.getCurrentDayOfWeek()
    var todayStreakDone = false

    init {
        if (quizResult == QuizResult.SUCCESS) {
            repository.addXp(xp)
            val lastPracticeDate = repository.getPracticeDate()

            repository.updatePracticeDate(TimeUtils.getCurrentDate())
            todayStreakDone = true
            if (lastPracticeDate == null) {
                repository.incrementStreak()
            } else {
                val differenceOfDays =
                    TimeUtils.subtractDates(TimeUtils.getCurrentDate(), lastPracticeDate)
                if (differenceOfDays > 0) {
                    repository.incrementStreak()
                }
            }
            viewModelScope.launch {
                exerciseId?.let { repository.unlockNextExercise(previousExerciseId = it) }
            }
        } else {
            val lastPracticeDate = repository.getPracticeDate()
            if(lastPracticeDate != null) {
                val differenceOfDays =
                    TimeUtils.subtractDates(TimeUtils.getCurrentDate(), lastPracticeDate)
                if(differenceOfDays == 0) {
                    todayStreakDone = true
                }
            }
        }

        streakCount = repository.getStreak()
    }
    private fun getBundle(): Bundle {
        val bundle = Bundle()
        bundle.putInt("dayOfWeek", dayOfWeek)
        bundle.putBoolean("todayStreakDone", todayStreakDone)
        bundle.putInt("streakCount", streakCount)
        bundle.putInt("xp", xp)
        bundle.putInt("totalXP", totalXP)
        return bundle
    }
    fun createChartsFragment(): Charts {
        val chartsFragment = Charts().apply {
            arguments = getBundle()
        }
        return chartsFragment
    }

}