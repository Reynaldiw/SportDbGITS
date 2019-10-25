package com.reynaldiwijaya.sportdbgits.data.footballclub.remote

import com.reynaldiwijaya.sportdbgits.base.repository.WebApi
import com.reynaldiwijaya.sportdbgits.data.footballclub.model.TeamResponseItem
import com.reynaldiwijaya.sportdbgits.data.footballclub.model.TeamItem

class FootballApi(private val api : FootballApiClient) : WebApi, FootballApiClient {
    override suspend fun getTeams(league: String): TeamResponseItem<List<TeamItem>> {
        return api.getTeams(league)
    }

}