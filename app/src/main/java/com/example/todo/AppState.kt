package com.example.todo

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log

@SuppressLint("StaticFieldLeak")
object AppState {
    private var activityContext: Context? = null

    fun initialize(context: Context){
        activityContext = context.applicationContext // store the activity directly
        Log.d("AppState", "Safe context Stored -- application context never leaks")
    }
}