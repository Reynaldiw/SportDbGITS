package com.reynaldiwijaya.sportdbgits.domain.football

import com.reynaldiwijaya.sportdbgits.data.footballclub.FootballRepository
import com.reynaldiwijaya.sportdbgits.domain.football.model.Team
import io.reactivex.Single

class FootballInteractor(private val repository: FootballRepository) : FootballUseCase {

    override fun getTeamsApi(league : String): Single<List<Team>> {
        return repository.getTeamsApi(league).map { it.map { teamItem ->
            teamItem.toTeam()
        } }
    }

    override fun getTeamsFromDatabase(): Single<List<Team>> {
        return repository.getTeamsDatabase().map { it.map { teamItem ->
            teamItem.toTeam()
        } }
    }

    override fun insertTeamToDatabase(team: Team) {
        repository.insertTeamToDatabase(team.toTeamItem())
    }

    override fun removeTeamFromDatabase(team: Team) {
        repository.removeTeamFromDatabase(team.toTeamItem())
    }

    override fun getTeamById(id: Int): Single<List<Team>> {
        return repository.getTeamById(id).map { it.map { it.toTeam() } }
    }

    override fun removeTeamById(id: Int) {
        repository.removeTeamById(id)
    }
}