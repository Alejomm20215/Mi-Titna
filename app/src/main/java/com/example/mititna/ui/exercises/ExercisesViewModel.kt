package com.example.mititna.ui.exercises

import androidx.lifecycle.ViewModel
import com.example.mititna.data.Repository

class ExercisesViewModel(
    repository: Repository
) : ViewModel() {

    val exercises = repository.getExercises()
}