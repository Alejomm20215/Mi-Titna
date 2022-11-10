package com.example.mititna.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "questions",
    foreignKeys = [
        ForeignKey(
            entity = Exercise::class,
            parentColumns = ["id"],
            childColumns = ["exerciseId"],
            onDelete = CASCADE
        )
    ]
)
data class Question(
    @PrimaryKey
    val id: String,
    val exerciseId: String,
    @ColumnInfo(name = "question_number")
    val questionNumber: Int,
    val question: String,
    @ColumnInfo(name = "question_type")
    val questionType: String,
    val hint: String,
    @ColumnInfo(name = "correct_answer")
    val correctAnswer: String,
    val options: Options
) {
    override fun toString(): String {
        return question
    }
}

data class Options(
    val data: List<String>
)