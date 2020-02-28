package app.sargis.khlopuzyan.alias.ui.teams

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import app.sargis.khlopuzyan.alias.R
import app.sargis.khlopuzyan.alias.databinding.LayoutRecyclerViewItemTeamBinding
import app.sargis.khlopuzyan.alias.model.TeamName
import app.sargis.khlopuzyan.alias.ui.common.BindableAdapter

/**
 * Created by Sargis Khlopuzyan, on 2/28/2020.
 *
 * @author Sargis Khlopuzyan (sargis.khlopuzyan@fcc.am)
 */
class TeamsAdapter(
    val viewModel: TeamsViewModel
) : RecyclerView.Adapter<TeamsAdapter.ViewHolder>(), BindableAdapter<TeamName> {

    private var teams = mutableListOf<TeamName>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: LayoutRecyclerViewItemTeamBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.layout_recycler_view_item_team,
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
        holder.bindData(teams[position], viewModel)
    }

    override fun setItems(items: TeamName?) {
        items?.let {
            if (!teams.contains(it)) {
                teams.add(it)
                notifyItemInserted(teams.size - 1)
            }
        }
    }

    class ViewHolder(binding: LayoutRecyclerViewItemTeamBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var binding: LayoutRecyclerViewItemTeamBinding = binding

        fun bindData(teamName: TeamName, viewModel: TeamsViewModel) {
            binding.viewModel = viewModel
            binding.textViewName.text = teamName.teamName
        }
    }

}