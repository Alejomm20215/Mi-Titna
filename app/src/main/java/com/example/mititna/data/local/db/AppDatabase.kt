package com.example.mititna.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.mititna.data.local.db.AppDatabase
import com.example.mititna.data.local.db.dao.ExerciseDao
import com.example.mititna.data.local.db.dao.LessonDao
import com.example.mititna.data.local.db.dao.QuestionDao
import com.example.mititna.data.model.db.Exercise
import com.example.mititna.data.model.db.Lesson
import com.example.mititna.data.model.db.Question
import com.example.mititna.helper.Converters
import com.example.mititna.helper.ParseJsonDataFile
import java.util.concurrent.Executors


@Database(
    entities = [Exercise::class, Lesson::class, Question::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDao

    abstract fun lessonDao(): LessonDao

    abstract fun questionDao(): QuestionDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context, module: String): AppDatabase? {
            synchronized(this) {
                var instance =
                    INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "Mi_Titna_App"
                    )
                        .fallbackToDestructiveMigration()
                        .addCallback(object : RoomDatabase.Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)
                                Executors.newSingleThreadExecutor().execute {
                                    instance?.let {db ->
                                        val (exercises, lessons, questions) = ParseJsonDataFile.parse(
                                            context, module
                                        )

                                        db.exerciseDao().insertAll(exercises)
                                        db.lessonDao().insertAll(lessons)
                                        db.questionDao().insertAll(questions)
                                    }
                                }
                            }
                        })
                        .build()
                    INSTANCE = instance
                }
                return INSTANCE
            }
        }
    }
}