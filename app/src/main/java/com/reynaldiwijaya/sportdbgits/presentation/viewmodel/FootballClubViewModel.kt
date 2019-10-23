package com.reynaldiwijaya.sportdbgits.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.reynaldiwijaya.sportdbgits.base.viewmodel.BaseViewModel
import com.reynaldiwijaya.sportdbgits.domain.football.FootballUseCase
import com.reynaldiwijaya.sportdbgits.domain.football.model.Team
import com.reynaldiwijaya.sportdbgits.utils.singleScheduler
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

sealed class HomeScreenState
object LoadingState : HomeScreenState()
data class ErrorState(var msg : String?) : HomeScreenState()
data class DatasLoadedState(var teams : List<Team>) : HomeScreenState()
data class SuccessInsertOrRemoveInDatabase(val state : Boolean) : HomeScreenState()

class FootballClubViewModel(private val usecase : FootballUseCase) : BaseViewModel() {

    var stateView = MutableLiveData<HomeScreenState>()

    fun getTeamsApi(league : String) {
        stateView.value = LoadingState
        disposable.add(
        usecase.getTeamsApi(league)
            .compose(singleScheduler())
            .subscribe( { result ->
                if (result.isNotEmpty()) {
                    stateView.value = DatasLoadedState(result)
                } else {
                    Log.e("API", "Gagal")
                }
            }, this::onError))
    }

    fun getTeamsFromDatabase() {
        stateView.value = LoadingState
        disposable.add(
            usecase.getTeamsFromDatabase()
                .compose(singleScheduler())
                .subscribe( {result ->
                    stateView.value = DatasLoadedState(result)
                }, this::onError)
        )
    }

    fun getTeamByIdFromDatabase(id : Int) {
        stateView.value = LoadingState
        disposable.add(
            usecase.getTeamById(id)
                .compose(singleScheduler())
                .subscribe({result ->
                    stateView.value = DatasLoadedState(result)
                }, this::onError)
        )
    }

    fun insertTeamToDatabase(team: Team) {
        stateView.value = LoadingState
        Completable.fromAction{usecase.insertTeamToDatabase(team)}
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver{
                override fun onComplete() {
                    stateView.value = SuccessInsertOrRemoveInDatabase(true)
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                    onError(e)
                }
            })
    }

    fun removeTeamFromDatabase(team : Team) {
        stateView.value = LoadingState
        Completable.fromAction{usecase.removeTeamFromDatabase(team)}
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver{
                override fun onComplete() {
                    stateView.value = SuccessInsertOrRemoveInDatabase(true)
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                    onError(e)
                }
            })
    }

    override fun onError(e: Throwable) {
        stateView.value = ErrorState(e.message)
    }
}