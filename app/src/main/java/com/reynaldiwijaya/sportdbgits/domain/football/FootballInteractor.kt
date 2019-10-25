package com.reynaldiwijaya.sportdbgits.domain.football

import com.reynaldiwijaya.sportdbgits.data.footballclub.FootballRepository
import com.reynaldiwijaya.sportdbgits.domain.football.model.Team

class FootballInteractor(private val repository: FootballRepository) : FootballUseCase {

    override suspend fun getTeamsApi(league : String): List<Team> {
        return repository.getTeamsApi(league).map { it.toTeam() }
    }

    override suspend fun getTeamsFromDatabase(): List<Team> {
        return repository.getTeamsDatabase().map { it.toTeam() }
    }

    override suspend fun insertTeamToDatabase(team: Team) {
        repository.insertTeamToDatabase(team.toTeamItem())
    }

    override suspend fun removeTeamFromDatabase(team: Team) {
        repository.removeTeamFromDatabase(team.toTeamItem())
    }

    override suspend fun getTeamById(id: Int): List<Team> {
        return repository.getTeamById(id).map { it.toTeam() }
    }

    override suspend fun removeTeamById(id: Int) {
        repository.removeTeamById(id)
    }
}