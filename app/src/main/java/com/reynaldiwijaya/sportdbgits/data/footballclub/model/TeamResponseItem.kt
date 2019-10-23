package com.reynaldiwijaya.sportdbgits.data.footballclub.model

import com.google.gson.annotations.SerializedName

data class TeamResponseItem<T> (
    @SerializedName("teams")
    val datas: T? = null
) : Model()