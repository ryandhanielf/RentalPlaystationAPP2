package com.example.rentalplaystationapp.repository

import com.example.rentalplaystationapp.dao.GamesDao
import com.example.rentalplaystationapp.model.Games
import kotlinx.coroutines.flow.Flow

class GamesRepository(private val gamesDao: GamesDao) {
    val allGames: Flow<List<Games>> = gamesDao.getAllGames()
    suspend fun insertGames(games: Games){
        gamesDao.insertGames(games)
    }

    suspend fun deleteGames(games: Games){
        gamesDao.deleteGames(games)
    }

    suspend fun updateGames(games: Games){
        gamesDao.updateGames(games)
    }
}