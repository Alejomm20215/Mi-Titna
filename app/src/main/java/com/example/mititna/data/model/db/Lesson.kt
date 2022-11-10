package com.example.mititna.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey


@Entity(
    tableName = "lessons",
    foreignKeys = [
        ForeignKey(
            entity = Exercise::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("exerciseId"),
            onDelete = CASCADE
        )
    ]
)
data class Lesson(
    @PrimaryKey
    val id: String,
    val exerciseId: String,
    @ColumnInfo(name = "lesson_number")
    val lessonNumber: Int,
    val phrase: String,
    val phonetics: String,
    val translation: String,
    val description: String,
    val examples: Examples
)

data class Examples(
    val data: List<Example>
)

data class Example(
    val id: String,
    val sentence: String,
    val translation: String
)