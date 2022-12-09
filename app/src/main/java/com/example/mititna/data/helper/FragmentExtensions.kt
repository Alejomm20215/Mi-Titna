package com.example.mititna.data.helper

import androidx.fragment.app.Fragment
import com.example.mititna.App
import com.example.mititna.ui.quiz.result.ResultViewModel

fun Fragment.getViewModelFactory(
    exerciseId: String? = "-1"
): ViewModelFactory {
    val repository = (requireContext().applicationContext as App).repository
    return ViewModelFactory(repository, exerciseId)
}

fun Fragment.getResultViewModelFactory(
    exerciseId: String? = "-1",
    quizResult: ResultViewModel.QuizResult,
    lifeLeft: Int
): ResultViewModelFactory {
    val repository = (requireContext().applicationContext as App).repository
    return ResultViewModelFactory(
        repository = repository,
        exerciseId = exerciseId,
        quizResult = quizResult,
        lifeLeft = lifeLeft
    )
}