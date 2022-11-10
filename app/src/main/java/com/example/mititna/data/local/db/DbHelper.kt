package com.example.mititna.data.local.db

import androidx.lifecycle.LiveData
import com.example.mititna.data.model.db.Exercise
import com.example.mititna.data.model.db.Lesson
import com.example.mititna.data.model.db.Question


interface DbHelper{

    suspend fun insertExercises(exercises: List<Exercise>): List<Long>

    fun getExercises(): LiveData<List<Exercise>>

    suspend fun unlockNextExercise(previousExerciseId: String)

    suspend fun insertLessons(lessons: List<Lesson>): List<Long>

    fun getLessonsByExerciseId(exerciseId: String): LiveData<List<Lesson>>

    suspend fun insertQuestions(questions: List<Question>): List<Long>

    fun getQuestionsByExerciseId(exerciseId: String): LiveData<List<Question>>
}