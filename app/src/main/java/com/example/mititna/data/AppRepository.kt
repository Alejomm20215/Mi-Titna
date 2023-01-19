package com.example.mititna.data

import androidx.lifecycle.LiveData
import com.example.mititna.data.local.db.DbHelper
import com.example.mititna.data.local.prefs.PreferencesHelper
import com.example.mititna.data.model.db.Exercise
import com.example.mititna.data.model.db.Lesson
import com.example.mititna.data.model.db.Question
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*


class AppRepository (
    private val dbHelper: DbHelper,
    private val preferencesHelper: PreferencesHelper,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : Repository {



    override suspend fun insertExercises(exercises: List<Exercise>): List<Long> = withContext(ioDispatcher){
        dbHelper.insertExercises(exercises)
    }

    override fun getExercises(): LiveData<List<Exercise>> =
        dbHelper.getExercises()

    override suspend fun unlockNextExercise(previousExerciseId: String) {
        withContext(ioDispatcher) {
            dbHelper.unlockNextExercise(previousExerciseId)
        }
    }

    override suspend fun insertLessons(lessons: List<Lesson>): List<Long> = withContext(ioDispatcher) {
        dbHelper.insertLessons(lessons)
    }

    override fun getLessonsByExerciseId(exerciseId: String): LiveData<List<Lesson>> =
        dbHelper.getLessonsByExerciseId(exerciseId)

    override suspend fun insertQuestions(questions: List<Question>): List<Long> =
        withContext(ioDispatcher) {
            dbHelper.insertQuestions(questions)
    }

    override fun getQuestionsByExerciseId(exerciseId: String): LiveData<List<Question>> =
        dbHelper.getQuestionsByExerciseId(exerciseId)

    override fun addXp(value: Int) {
        preferencesHelper.addXp(value)
    }

    override fun getXp(): Int = preferencesHelper.getXp()

    override fun updatePracticeDate(date: Date) {
        preferencesHelper.updatePracticeDate(date)
    }

    override fun getPracticeDate(): Date? = preferencesHelper.getPracticeDate()

    override fun incrementStreak() {
        preferencesHelper.incrementStreak()
    }

    override fun resetStreak() {
        preferencesHelper.resetStreak()
    }

    override fun getStreak(): Int = preferencesHelper.getStreak()
}