package app.sargis.khlopuzyan.alias.ui.startGame

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import app.sargis.khlopuzyan.alias.App
import app.sargis.khlopuzyan.alias.R
import app.sargis.khlopuzyan.alias.databinding.LayoutRecyclerViewItemScoreBinding
import app.sargis.khlopuzyan.alias.gameEngine.GameEngine
import app.sargis.khlopuzyan.alias.model.Team
import app.sargis.khlopuzyan.alias.model.Word
import app.sargis.khlopuzyan.alias.ui.common.BindableAdapter


/**
 * Created by Sargis Khlopuzyan, on 2/28/2020.
 *
 * @author Sargis Khlopuzyan (sargis.khlopuzyan@fcc.am)
 */
class ScoreAdapter(
    val viewModel: StartGameViewModel
) : RecyclerView.Adapter<ScoreAdapter.ViewHolder>(), BindableAdapter<GameEngine> {

    init {
        viewModel.updateScore()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: LayoutRecyclerViewItemScoreBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.layout_recycler_view_item_score,
            parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return viewModel.getTeamsCount()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val team = viewModel.gameEngineLiveData.value?.teams?.get(position)
        team?.let {
            holder.bindData(it)
        }
    }

    override fun setItem(data: GameEngine?) {
        data?.let {
            notifyDataSetChanged()
        }
    }

    class ViewHolder(
        var binding: LayoutRecyclerViewItemScoreBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(team: Team) {
            binding.textViewTeamName.text = "${team.name}"
            binding.textViewScore.text = "${team.totalScore}"
        }
    }

}