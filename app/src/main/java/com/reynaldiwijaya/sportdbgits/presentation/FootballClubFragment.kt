package com.reynaldiwijaya.sportdbgits.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.reynaldiwijaya.sportdbgits.R
import com.reynaldiwijaya.sportdbgits.domain.football.model.Team
import com.reynaldiwijaya.sportdbgits.presentation.adapter.FootballClubAdapter
import com.reynaldiwijaya.sportdbgits.presentation.detail.DetailFootballClubActivity
import com.reynaldiwijaya.sportdbgits.presentation.viewmodel.*
import com.reynaldiwijaya.sportdbgits.utils.*
import kotlinx.android.synthetic.main.fragment_football_club.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FootballClubFragment : Fragment(), FootballClubAdapter.OnItemClick {

    companion object {
        fun instance(state: String): FootballClubFragment {
            val frag = FootballClubFragment()
            val bundle = Bundle()
            bundle.putString(Keys.KEY_STATE_LIST_CLUB, state)
            frag.arguments = bundle

            return frag
        }
    }

    private val viewModel : FootballClubViewModel by viewModel()

    private var state : String? = emptyString()

    private lateinit var footballClubAdapter: FootballClubAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        state = arguments?.getString(Keys.KEY_STATE_LIST_CLUB)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_football_club, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        srTeam.setOnRefreshListener {
            initUI()
        }

        initUI()
    }

    private fun initUI() {
        setUpRecyclerView()
        when(state) {
            FootballListState.API.state -> {
                viewModel.getTeamsApi(getString(R.string.label_english_league))
                viewModel.stateView.observe(this, stateObserver)
            }
            FootballListState.FAVORITE.state -> {
                viewModel.getTeamsFromDatabase()
                viewModel.stateView.observe(this, stateObserver)
            }
        }
    }

    private fun setUpRecyclerView() {
        rvTeam?.apply {
            layoutManager = LinearLayoutManager(activity)
            footballClubAdapter = FootballClubAdapter()
            footballClubAdapter.itemClicked(this@FootballClubFragment)
            adapter = footballClubAdapter
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        }
    }

    override fun onItemClicked(view: View, team: Team) {
        activity?.let { DetailFootballClubActivity.start(it, team) }
    }

    private val stateObserver = Observer<HomeScreenState> {state ->
        when(state) {
            is LoadingState -> {
                pbTeam.visible()
                rvTeam.gone()
                tvEmptyDatas.gone()
            }
            is ErrorState -> {
                pbTeam.gone()
                rvTeam.gone()
                tvEmptyDatas.visible()
                tvEmptyDatas.text = state.msg
            }
            is DatasLoadedState -> {
                pbTeam.gone()
                rvTeam.visible()
                srTeam.isRefreshing = false

                if (state.teams.isEmpty()) {
                    tvEmptyDatas.visible()
                    rvTeam.gone()
                } else {
                    tvEmptyDatas.gone()
                    footballClubAdapter.setDatas(state.teams.toMutableList())
                }
            }
        }
    }
}
