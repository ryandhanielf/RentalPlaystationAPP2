package com.example.rentalplaystationapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.rentalplaystationapp.model.Games
import com.example.rentalplaystationapp.repository.GamesRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class GamesViewModel(private val repository: GamesRepository): ViewModel() {
    val allGames: LiveData<List<Games>> = repository.allGames.asLiveData()

    fun insert(games: Games) = viewModelScope.launch {
        repository.insertGames(games)
    }

    fun delete(games: Games) = viewModelScope.launch {
        repository.deleteGames(games)
    }

    fun update(games: Games) = viewModelScope.launch {
        repository.updateGames(games)
    }
}

class GamesViewModelFactory(private val repository: GamesRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom((GamesViewModel::class.java))){
            return GamesViewModel(repository) as T
        }
     throw IllegalArgumentException("Unknown ViewModel class")
    }
}