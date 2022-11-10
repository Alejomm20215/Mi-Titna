package com.example.mititna.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercises")
data class Exercise(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "exercise_number")
    val exerciseNumber: Int,
    val title: String,
    val description: String,
    val isUnlocked: Boolean = false
)