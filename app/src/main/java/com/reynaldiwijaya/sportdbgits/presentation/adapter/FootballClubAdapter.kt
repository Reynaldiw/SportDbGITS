package com.reynaldiwijaya.sportdbgits.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.reynaldiwijaya.sportdbgits.R
import com.reynaldiwijaya.sportdbgits.domain.football.model.Team
import com.reynaldiwijaya.sportdbgits.utils.loadImage
import kotlinx.android.synthetic.main.item_team.view.*

class FootballClubAdapter : RecyclerView.Adapter<FootballClubAdapter.ViewHolder>() {

    private lateinit var onItemClick : OnItemClick

    private val datas : MutableList<Team> = mutableListOf()

    fun setDatas(teams : MutableList<Team>) {
        this.datas.clear()
        this.datas.addAll(teams)
        notifyDataSetChanged()
    }

    fun itemClicked(onItemClick: OnItemClick) {
        this.onItemClick = onItemClick
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_team, null)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val team : Team = datas[position]
        holder.bindItem(team, onItemClick)
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        fun bindItem(team : Team, onItemClick: OnItemClick) {
            with(itemView) {
                team.let {
                    tvTeamName.text = it.teamName
                    it.teamLogo?.let { it1 -> imgTeamLogo.loadImage(it1, itemView.context) }
                }
            }
            itemView.setOnClickListener {
                onItemClick.onItemClicked(it, team)
            }
        }
    }

    interface OnItemClick {
        fun onItemClicked(view: View, team: Team)
    }

}