package com.example.mititna

import android.app.Application
import com.example.mititna.data.Repository
import com.example.mititna.data.helper.ServiceLocator
import com.example.mititna.ui.modules.Modules


class App : Application() {

    val repository: Repository
        get() = ServiceLocator.provideRepository(this, Modules.MODULE_1.module)


}