package com.example.mititna.helper

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mititna.data.Repository
import com.example.mititna.ui.exercises.ExercisesViewModel
import com.example.mititna.ui.lessons.LessonsViewModel
import com.example.mititna.ui.quiz.QuizViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val repository: Repository,
    private val exerciseId: String?
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        with(modelClass) {
            when {
                isAssignableFrom(ExercisesViewModel::class.java) ->
                    ExercisesViewModel(repository)
                isAssignableFrom(LessonsViewModel::class.java) ->
                    LessonsViewModel(repository, exerciseId)
                isAssignableFrom(QuizViewModel::class.java) ->
                    QuizViewModel(repository, exerciseId)
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}