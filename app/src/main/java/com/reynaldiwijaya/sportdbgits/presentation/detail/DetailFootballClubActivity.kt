package com.reynaldiwijaya.sportdbgits.presentation.detail

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.reynaldiwijaya.sportdbgits.R
import com.reynaldiwijaya.sportdbgits.domain.football.model.Team
import com.reynaldiwijaya.sportdbgits.presentation.viewmodel.*
import com.reynaldiwijaya.sportdbgits.utils.*
import kotlinx.android.synthetic.main.activity_detail_football_club.*
import org.jetbrains.anko.startActivity
import org.koin.android.viewmodel.ext.android.viewModel

class DetailFootballClubActivity : AppCompatActivity() {

    companion object {
        fun start(context: Context, team : Team) {
            context.startActivity<DetailFootballClubActivity>(Keys.KEY_DATA_TEAM to team)
        }
    }

    private lateinit var isInsertOrRemove : String
    private lateinit var data : Team
    private val viewModel : FootballClubViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_football_club)

        data = intent.getSerializableExtra(Keys.KEY_DATA_TEAM) as Team

        initUi()
    }

    private fun initUi() {
        viewModel.apply {
            data.idTeam?.let { getTeamByIdFromDatabase(it) }
            stateView.observe(this@DetailFootballClubActivity, Observer {state ->
                when(state) {
                    is LoadingState -> {}
                    is ErrorState -> {}
                    is DatasLoadedState -> {
                        if (state.teams.isNotEmpty()) {
                            state.teams.forEach { setUpDataToView(it) }
                        } else {
                            setUpDataToView(data)
                        }
                    }
                }
            })
        }
    }

    private fun setUpDataToView(data : Team?) {
        data?.let {
            tvTeamName.text = it.teamName
            tvTeamDesc.text = it.teamDesc
            it.teamLogo?.let { it1 -> imgTeamLogo.loadImage(it1) }
        }
        setUpFavorite(data)
    }

    private fun setUpFavorite(data: Team?) {
        if (data?.teamFavorite == true) {
            btnFavorite.setFavoriteImage(this, true)
            btnFavorite.onClick { removeTeamFromDatabase(data) }
        } else {
            btnFavorite.setFavoriteImage(this, false)
            btnFavorite.onClick {
                if (data != null) {
                    insertTeamToDatabase(data)
                }
            }
        }
    }

    private fun removeTeamFromDatabase(data : Team) {
        viewModel.apply {
            removeTeamFromDatabase(Team(data.idTeam, data.teamName, data.teamLogo, data.teamDesc, false))
            isInsertOrRemove = InsertOrRemoveState.REMOVE.value
            stateView.observe(this@DetailFootballClubActivity, stateObserverFavorite)
        }
    }

    private fun insertTeamToDatabase(data: Team) {
        viewModel.apply {
            insertTeamToDatabase(Team(data.idTeam, data.teamName, data.teamLogo, data.teamDesc, true))
            isInsertOrRemove = InsertOrRemoveState.INSERT.value
            stateView.observe(this@DetailFootballClubActivity, stateObserverFavorite)
        }
    }

    private val stateObserverFavorite = Observer<HomeScreenState> {state ->
        when(state) {
            is LoadingState -> {}
            is ErrorState -> {}
            is SuccessInsertOrRemoveInDatabase -> {
                when(isInsertOrRemove) {
                    InsertOrRemoveState.REMOVE.value -> {
                        showMessage(nsvDetailClub, getString(R.string.message_success_to_delete))
                        btnFavorite.setFavoriteImage(this, false)
                    }
                    InsertOrRemoveState.INSERT.value -> {
                        showMessage(nsvDetailClub, getString(R.string.message_success_to_add))
                        btnFavorite.setFavoriteImage(this, true)
                    }
                }
            }
        }
    }
}

