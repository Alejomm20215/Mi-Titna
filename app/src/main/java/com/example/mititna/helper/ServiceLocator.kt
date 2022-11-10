package com.example.mititna.helper

import android.content.Context
import com.example.mititna.data.AppRepository
import com.example.mititna.data.Repository
import com.example.mititna.data.local.db.AppDatabase
import com.example.mititna.data.local.db.AppDbHelper
import com.example.mititna.data.local.db.DbHelper
import com.example.mititna.data.local.prefs.AppPreferencesHelper
import com.example.mititna.data.local.prefs.PreferencesHelper

object ServiceLocator {

    @Volatile private var repository: Repository? = null

    fun provideRepository(context: Context): Repository {
        synchronized(this) {
            return repository ?: repository ?: createRepository(context, "module_1")
        }
    }

    private fun createRepository(context: Context, module: String): Repository =
        AppRepository(
            createDbHelper(context, module),
            createPreferenceHelper(context)
        )

    private fun createPreferenceHelper(context: Context): PreferencesHelper =
        AppPreferencesHelper.getInstance(context)

    private fun createDbHelper(context: Context, module: String): DbHelper =
        AppDbHelper.getInstance(createAppDatabase(context, module))

    private fun createAppDatabase(context: Context, module: String): AppDatabase =
        AppDatabase.getInstance(context, module)!!
}