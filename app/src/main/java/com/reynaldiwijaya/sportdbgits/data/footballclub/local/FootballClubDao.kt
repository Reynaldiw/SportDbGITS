package com.reynaldiwijaya.sportdbgits.data.footballclub.local

import androidx.room.Dao
import androidx.room.Query
import com.reynaldiwijaya.sportdbgits.data.footballclub.model.TeamItem
import com.reynaldiwijaya.sportdbgits.domain.football.model.Team
import io.reactivex.Single

@Dao
abstract class FootballClubDao : RxLocalDb<TeamItem> {

    @Query("SELECT * FROM team")
    abstract override fun getAllTeams(): List<TeamItem>

    @Query("SELECT * FROM team WHERE idTeam == :id")
    abstract override fun getTeamById(id: Int): List<TeamItem>

    @Query("DELETE FROM team WHERE idTeam == :id")
    abstract override fun removeTeamById(id: Int)
}