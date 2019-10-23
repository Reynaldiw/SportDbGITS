package com.reynaldiwijaya.sportdbgits.data.footballclub

import com.reynaldiwijaya.sportdbgits.base.repository.BaseRepository
import com.reynaldiwijaya.sportdbgits.data.footballclub.model.TeamItem
import com.reynaldiwijaya.sportdbgits.domain.football.model.Team
import io.reactivex.Single

interface FootballRepository : BaseRepository {

    fun getTeamsApi(league : String) : Single<List<TeamItem>>

    fun getTeamsDatabase() : Single<List<TeamItem>>

    fun insertTeamToDatabase(team : TeamItem)

    fun removeTeamFromDatabase(team: TeamItem)

    fun getTeamById(id : Int) : Single<List<TeamItem>>

    fun removeTeamById(id : Int)
}