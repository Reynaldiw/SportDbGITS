package com.reynaldiwijaya.sportdbgits.data.footballclub.remote

import com.reynaldiwijaya.sportdbgits.data.footballclub.model.TeamResponseItem
import com.reynaldiwijaya.sportdbgits.data.footballclub.model.TeamItem
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FootballApiClient {

    @GET("v1/json/1/search_all_teams.php")
    suspend fun getTeams(@Query("l") league : String) : TeamResponseItem<List<TeamItem>>
}