package app.sargis.khlopuzyan.alias.ui.teams

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import app.sargis.khlopuzyan.alias.R
import app.sargis.khlopuzyan.alias.databinding.LayoutRecyclerViewItemTeamNameBinding
import app.sargis.khlopuzyan.alias.model.Team
import app.sargis.khlopuzyan.alias.ui.common.BindableAdapter

/**
 * Created by Sargis Khlopuzyan, on 2/28/2020.
 *
 * @author Sargis Khlopuzyan (sargis.khlopuzyan@fcc.am)
 */
class TeamsAdapter(
    val viewModel: TeamsViewModel
) : RecyclerView.Adapter<TeamsAdapter.ViewHolder>(), BindableAdapter<List<Team>> {

    private var teams = mutableListOf<Team>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: LayoutRecyclerViewItemTeamNameBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.layout_recycler_view_item_team_name,
            parent, false
        )
        return ViewHolder(
            binding
        )
    }

    override fun getItemCount(): Int {
        return teams.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(teams[position], teams.size, viewModel)
    }

    override fun setItem(data: List<Team>?) {
        teams.clear()
        data?.let {
            for (team in it) {
                if (!teams.contains(team)) {
                    teams.add(team)
                    notifyDataSetChanged()
                }
            }
        }
    }

    class ViewHolder(binding: LayoutRecyclerViewItemTeamNameBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var binding: LayoutRecyclerViewItemTeamNameBinding = binding

        fun bindData(team: Team, size: Int, viewModel: TeamsViewModel) {
            binding.viewModel = viewModel
            binding.textViewName.text = team.name

            itemView.setOnLongClickListener {
                viewModel.onChangeTeamNameClick(team)
                return@setOnLongClickListener true
            }


            if (size > 2) {
                binding.buttonDelete.visibility = View.VISIBLE
                binding.buttonDelete.setOnClickListener {
                    viewModel.onDeleteTeamClick(team)
                }
            } else {
                binding.buttonDelete.visibility = View.INVISIBLE
            }
        }
    }

}