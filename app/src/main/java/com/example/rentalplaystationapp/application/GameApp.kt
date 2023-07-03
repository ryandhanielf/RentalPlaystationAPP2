package com.example.rentalplaystationapp.application

import android.app.Application
import com.example.rentalplaystationapp.repository.GamesRepository

class GameApp: Application() {
    val database by lazy { GameDatabase.getDatabase(this) }
    val repository by lazy { GamesRepository(database.GamesDao()) }
}