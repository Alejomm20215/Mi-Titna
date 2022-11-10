package com.example.mititna.data.local.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.mititna.data.model.db.Question

@Dao
interface QuestionDao : BaseDao<Question> {

    @Query("SELECT * FROM questions WHERE exerciseId = :exerciseId")
    fun getQuestionsByExerciseId(exerciseId: String): LiveData<List<Question>>
}