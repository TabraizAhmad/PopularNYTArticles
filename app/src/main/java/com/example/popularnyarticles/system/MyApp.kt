package com.example.popularnyarticles.system

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class MyApp : Application(){
    open fun getBaseUrl() = "https://api.nytimes.com/svc/"

}