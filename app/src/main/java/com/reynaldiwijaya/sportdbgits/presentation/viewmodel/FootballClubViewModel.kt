package com.reynaldiwijaya.sportdbgits.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.reynaldiwijaya.sportdbgits.base.viewmodel.BaseViewModel
import com.reynaldiwijaya.sportdbgits.domain.football.FootballUseCase
import com.reynaldiwijaya.sportdbgits.domain.football.model.Team
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

sealed class HomeScreenState
object LoadingState : HomeScreenState()
data class ErrorState(var msg : String?) : HomeScreenState()
data class DatasLoadedState(var teams : List<Team>) : HomeScreenState()
data class SuccessInsertOrRemoveInDatabase(val state : Boolean) : HomeScreenState()
data class DataLoadedState(var team : Team) : HomeScreenState()

class FootballClubViewModel(private val usecase : FootballUseCase) : BaseViewModel() {

    var stateView = MutableLiveData<HomeScreenState>()

    fun getTeamsApi(league : String) {
        stateView.value = LoadingState

        viewModelScope.launch {
            val teams = withContext(Dispatchers.IO) {
                usecase.getTeamsApi(league)
            }

            try {
                stateView.value = DatasLoadedState(teams)
            } catch (e : Exception) {
                stateView.value = ErrorState(e.message)
            }
        }
    }

    fun getTeamsFromDatabase() {
        stateView.value = LoadingState
        viewModelScope.launch {
            val teams = withContext(Dispatchers.IO) {
                usecase.getTeamsFromDatabase()
            }

            try {
                stateView.value = DatasLoadedState(teams)
            } catch (e : Exception) {
                stateView.value = ErrorState(e.message)
            }
        }
    }

    fun getTeamByIdFromDatabase(id : Int) {
        stateView.value = LoadingState
        viewModelScope.launch {
            val team = withContext(Dispatchers.IO) {
                usecase.getTeamById(id)
            }

            try {
                stateView.value = DatasLoadedState(team)
            } catch (e : Exception) {
                stateView.value = ErrorState(e.message)
            }
        }
    }

    fun insertTeamToDatabase(team: Team) {
        stateView.value = LoadingState
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    usecase.insertTeamToDatabase(team)
                }
                stateView.value = SuccessInsertOrRemoveInDatabase(true)
            }catch (e : Exception) {
                stateView.value = ErrorState(e.message)
            }
        }
    }

    fun removeTeamFromDatabase(team : Team) {
        stateView.value = LoadingState
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    usecase.removeTeamFromDatabase(team)
                }
                stateView.value = SuccessInsertOrRemoveInDatabase(true)
                stateView.value = DataLoadedState(team.copy(teamFavorite = false))
            }catch (e : Exception) {
                stateView.value = ErrorState(e.message)
            }
        }
    }

    override fun onError(e: Throwable) {
        stateView.value = ErrorState(e.message)
    }
}