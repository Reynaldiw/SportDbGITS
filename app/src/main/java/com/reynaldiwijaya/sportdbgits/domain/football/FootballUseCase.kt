package com.reynaldiwijaya.sportdbgits.domain.football

import com.reynaldiwijaya.sportdbgits.domain.football.model.Team
import io.reactivex.Single

interface FootballUseCase {

    fun getTeamsApi(league : String) : Single<List<Team>>

    fun getTeamsFromDatabase() : Single<List<Team>>

    fun insertTeamToDatabase(team: Team)

    fun removeTeamFromDatabase(team: Team)

    fun getTeamById(id : Int) : Single<List<Team>>

    fun removeTeamById(id : Int)
}