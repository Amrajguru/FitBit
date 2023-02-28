package com.example.fitbit

import android.app.Application

class InputApplication : Application() {
    val db by lazy { AppDatabase.getInstance(this) }
}