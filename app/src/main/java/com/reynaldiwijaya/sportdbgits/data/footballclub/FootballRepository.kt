package com.reynaldiwijaya.sportdbgits.data.footballclub

import com.reynaldiwijaya.sportdbgits.base.repository.BaseRepository
import com.reynaldiwijaya.sportdbgits.data.footballclub.model.TeamItem
import com.reynaldiwijaya.sportdbgits.domain.football.model.Team
import io.reactivex.Single

interface FootballRepository : BaseRepository {

    suspend fun getTeamsApi(league : String) : List<TeamItem>

    suspend fun getTeamsDatabase() : List<TeamItem>

    suspend fun insertTeamToDatabase(team : TeamItem)

    suspend fun removeTeamFromDatabase(team: TeamItem)

    suspend fun getTeamById(id : Int) : List<TeamItem>

    suspend fun removeTeamById(id : Int)
}