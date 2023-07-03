package com.example.rentalplaystationapp.application

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rentalplaystationapp.dao.GamesDao
import com.example.rentalplaystationapp.model.Games

@Database(entities = [Games::class], version = 1, exportSchema = false)
abstract class GameDatabase: RoomDatabase() {
    abstract  fun GamesDao(): GamesDao

    companion object{
        private var INSTANCE: GameDatabase? = null

        fun getDatabase(context: Context): GameDatabase {
            return INSTANCE ?: synchronized(lock = this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GameDatabase::class.java,
                    "game_database_1"
                )
                    .allowMainThreadQueries()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}