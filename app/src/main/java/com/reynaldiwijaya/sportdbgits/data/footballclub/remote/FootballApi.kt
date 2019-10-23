package com.reynaldiwijaya.sportdbgits.data.footballclub.remote

import com.reynaldiwijaya.sportdbgits.base.repository.WebApi
import com.reynaldiwijaya.sportdbgits.data.footballclub.model.TeamResponseItem
import com.reynaldiwijaya.sportdbgits.data.footballclub.model.TeamItem
import io.reactivex.Single
import retrofit2.Response

class FootballApi(private val api : FootballApiClient) : WebApi, FootballApiClient {
    override fun getTeams(league: String): Single<Response<TeamResponseItem<List<TeamItem>>>> {
        return api.getTeams(league)
    }

}