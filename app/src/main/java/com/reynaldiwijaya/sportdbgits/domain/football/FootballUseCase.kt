package com.reynaldiwijaya.sportdbgits.domain.football

import com.reynaldiwijaya.sportdbgits.domain.football.model.Team
import io.reactivex.Single

interface FootballUseCase {

    suspend fun getTeamsApi(league : String) : List<Team>

    fun getTeamsFromDatabase() : List<Team>

    fun insertTeamToDatabase(team: Team)

    fun removeTeamFromDatabase(team: Team)

    fun getTeamById(id : Int) : List<Team>

    fun removeTeamById(id : Int)
}