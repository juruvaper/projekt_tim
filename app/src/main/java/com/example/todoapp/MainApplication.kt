package com.example.todoapp

import android.app.Application
import androidx.room.Room
import com.example.todoapp.bazaDanych.ToDoDATABASE

class MainApplication : Application() {

    companion object {
        lateinit var toDoDATABASE: ToDoDATABASE
    }

    override fun onCreate() {
        super.onCreate()
        toDoDATABASE = Room.databaseBuilder(
            applicationContext,
            ToDoDATABASE::class.java,
            ToDoDATABASE.NAME
        ).build()
    }
}