package com.example.mititna.data.local.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.mititna.data.model.db.Exercise

@Dao
interface ExerciseDao : BaseDao<Exercise> {

    @Query("SELECT * FROM exercises")
    fun getExercises() : LiveData<List<Exercise>>

    @Query("SELECT * FROM exercises WHERE id = :exerciseId")
    fun getExerciseById(exerciseId: String): Exercise?

    @Query("UPDATE exercises SET isUnlocked = 1 WHERE exercise_number = ((SELECT exercise_number from exercises WHERE id = :previousExerciseId LIMIT 1) + 1)")
    fun unlockNextExercise(previousExerciseId: String)
}