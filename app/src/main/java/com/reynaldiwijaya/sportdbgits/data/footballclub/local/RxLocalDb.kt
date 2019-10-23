package com.reynaldiwijaya.sportdbgits.data.footballclub.local

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import com.reynaldiwijaya.sportdbgits.base.repository.LocalDb
import com.reynaldiwijaya.sportdbgits.data.footballclub.model.Model
import com.reynaldiwijaya.sportdbgits.domain.football.model.Team
import io.reactivex.Single

interface RxLocalDb<T : Model> : LocalDb {

    // Basic functions, no need to override

    @Insert(onConflict = REPLACE)
    fun insert(vararg response : T )

    @Delete
    fun remove(vararg response : T)

    // Basic functions, no need to override

    fun getAllTeams() : Single<List<T>>

    fun getTeamById(id : Int) : Single<List<T>>

    fun removeTeamById(id : Int)

}