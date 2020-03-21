package app.sargis.khlopuzyan.alias.ui.winner

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import app.sargis.khlopuzyan.alias.R
import app.sargis.khlopuzyan.alias.databinding.LayoutRecyclerViewItemWinnerBinding
import app.sargis.khlopuzyan.alias.model.Team
import app.sargis.khlopuzyan.alias.ui.common.BindableAdapter


/**
 * Created by Sargis Khlopuzyan, on 2/28/2020.
 *
 * @author Sargis Khlopuzyan (sargis.khlopuzyan@fcc.am)
 */
class WinnerAdapter(
    val viewModel: WinnerViewModel
) : RecyclerView.Adapter<WinnerAdapter.ViewHolder>(), BindableAdapter<MutableList<Team>> {

    var teams: MutableList<Team> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: LayoutRecyclerViewItemWinnerBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.layout_recycler_view_item_winner,
            parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return teams.size - 1
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position < teams.size) {
            val team = teams[position + 1]
            holder.bindData(team)
        }
    }

    override fun setItem(data: MutableList<Team>?) {
        teams.clear()
        data?.let {
            teams.addAll(data)
        }
        notifyDataSetChanged()
    }

    class ViewHolder(
        var binding: LayoutRecyclerViewItemWinnerBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(team: Team) {
            binding.textViewTeamPosition.text = "${(adapterPosition + 2)}"
            binding.textViewTeamName.text = "${team.name}"
            binding.textViewTeamTotalScore.text = "${team.totalScore}"
        }

    }
}