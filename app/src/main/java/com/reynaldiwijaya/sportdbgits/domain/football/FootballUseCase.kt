package com.reynaldiwijaya.sportdbgits.domain.football

import com.reynaldiwijaya.sportdbgits.domain.football.model.Team

interface FootballUseCase {

    suspend fun getTeamsApi(league : String) : List<Team>

    suspend fun getTeamsFromDatabase() : List<Team>

    suspend fun insertTeamToDatabase(team: Team)

    suspend fun removeTeamFromDatabase(team: Team)

    suspend fun getTeamById(id : Int) : List<Team>

    suspend fun removeTeamById(id : Int)
}