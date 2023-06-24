package com.example.rentalplaystationapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.rentalplaystationapp.model.Games
import kotlinx.coroutines.flow.Flow

@Dao
interface GamesDao {
    @Query("SELECT * FROM 'games_table' ORDER BY name ASC")
    fun getAllGames(): Flow<List<Games>>

    @Insert
    suspend fun insertGames(games: Games)

    @Delete
    suspend fun deleteGames(games: Games)

    @Update fun updateGames(games: Games)
}