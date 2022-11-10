package com.example.mititna.data.local.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.mititna.data.model.db.Lesson

@Dao
interface LessonDao : BaseDao<Lesson> {

    @Query("SELECT * FROM lessons WHERE exerciseId = :exerciseId")
    fun getLessonsByExerciseId(exerciseId: String): LiveData<List<Lesson>>
}