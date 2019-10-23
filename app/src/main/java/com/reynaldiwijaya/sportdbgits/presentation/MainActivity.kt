package com.reynaldiwijaya.sportdbgits.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.reynaldiwijaya.sportdbgits.R
import com.reynaldiwijaya.sportdbgits.presentation.adapter.SportPagerAdapter
import com.reynaldiwijaya.sportdbgits.utils.FootballListState
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val pagerAdapter by lazy {
        SportPagerAdapter(
            supportFragmentManager,
            getMainFragments(),
            getTitlesTab()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpViewPager()

    }

    private fun setUpViewPager() {
        tabLayoutOptionFeature.tabIconTint = null

        vpFeature.adapter = pagerAdapter
        vpFeature.swipeEnabled = false
        vpFeature.offscreenPageLimit = 2
        tabLayoutOptionFeature.setupWithViewPager(vpFeature)
    }

    private fun getMainFragments(): List<Fragment> {
        return listOf(
            FootballClubFragment.instance(FootballListState.API.state),
            FootballClubFragment.instance(FootballListState.FAVORITE.state)
        )
    }

    private fun getTitlesTab(): List<String> {
        return listOf(
            getString(R.string.label_football_club),
            getString(R.string.label_favorite)
        )
    }
}
