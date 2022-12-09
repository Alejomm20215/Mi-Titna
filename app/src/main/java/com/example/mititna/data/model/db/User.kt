package com.example.mititna.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User (
    @PrimaryKey(autoGenerate = true) val user_id : Int?,
    @ColumnInfo(name = "first_name") val firstName : String,
    @ColumnInfo(name = "last_name") val lastName : String,
    @ColumnInfo(name = "roll_num") val rollNum : Int?
    )