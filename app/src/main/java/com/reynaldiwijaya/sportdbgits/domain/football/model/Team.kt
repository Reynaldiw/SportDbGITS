package com.reynaldiwijaya.sportdbgits.domain.football.model

import com.google.gson.annotations.SerializedName
import com.reynaldiwijaya.sportdbgits.data.footballclub.model.TeamItem
import java.io.Serializable

data class Team (
    @SerializedName("idTeam")
    val idTeam : Int?,

    @SerializedName("strTeam")
    val teamName : String?,

    @SerializedName("strTeamBadge")
    val teamLogo : String?,

    @SerializedName("strDescriptionEN")
    val teamDesc : String?,

    var teamFavorite : Boolean?
) : Serializable {
    fun toTeamItem() : TeamItem {
        return TeamItem(idTeam , teamName, teamLogo, teamDesc, teamFavorite)
    }
}