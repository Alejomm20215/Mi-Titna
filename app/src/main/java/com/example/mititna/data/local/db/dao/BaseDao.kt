package com.example.mititna.data.local.db.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<T>{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<T>): List<Long>
    @Update
    fun update(entity: T): Int
}