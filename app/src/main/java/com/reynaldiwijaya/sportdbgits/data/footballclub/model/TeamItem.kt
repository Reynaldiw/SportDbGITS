package com.reynaldiwijaya.sportdbgits.data.footballclub.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.reynaldiwijaya.sportdbgits.domain.football.model.Team

@Entity(tableName = "team")
data class TeamItem(
    @PrimaryKey
    @SerializedName("idTeam")
    val idTeam : Int?,
    @SerializedName("strTeam")
    val teamName : String?,
    @SerializedName("strTeamBadge")
    val teamLogo : String?,
    @SerializedName("strDescriptionEN")
    val teamDesc : String?,
    var teamFavorite : Boolean?
) : Model() {

    fun toTeam() : Team {
        return Team(
            teamDesc = teamDesc,
            teamName = teamName,
            teamLogo = teamLogo,
            idTeam = idTeam,
            teamFavorite = teamFavorite
        )
    }
}