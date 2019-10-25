package com.reynaldiwijaya.sportdbgits.data.footballclub

import com.reynaldiwijaya.sportdbgits.data.footballclub.local.FootballClubDao
import com.reynaldiwijaya.sportdbgits.data.footballclub.model.TeamItem
import com.reynaldiwijaya.sportdbgits.data.footballclub.remote.FootballApi
import com.reynaldiwijaya.sportdbgits.domain.football.model.Team
import io.reactivex.Single

class FootballDataStore(
    api : FootballApi,
    dao : FootballClubDao ) : FootballRepository {

    override val webService = api
    override val localDb = dao


    override suspend fun getTeamsApi(league : String): List<TeamItem>{
        return webService.getTeams(league).datas ?: listOf()
    }

    override fun getTeamsDatabase(): List<TeamItem> {
        return localDb.getAllTeams()
    }

    override fun insertTeamToDatabase(team: TeamItem) {
        localDb.insert(team)
    }

    override fun removeTeamFromDatabase(team: TeamItem) {
        localDb.remove(team)
    }

    override fun getTeamById(id : Int): List<TeamItem> {
        return localDb.getTeamById(id)
    }

    override fun removeTeamById(id: Int) {
        localDb.removeTeamById(id)
    }


}