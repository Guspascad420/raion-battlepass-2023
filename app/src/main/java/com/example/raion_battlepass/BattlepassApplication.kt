package com.example.raion_battlepass

import android.app.Application
import com.example.raion_battlepass.data.AppContainer
import com.example.raion_battlepass.data.DefaultAppContainer

class BattlepassApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}