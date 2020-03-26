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
import app.sargis.khlopuzyan.alias.ui.common.BindableAdapter


/**
 * Created by Sargis Khlopuzyan, on 2/28/2020.
 *
 * @author Sargis Khlopuzyan (sargis.khlopuzyan@fcc.am)
 */
class ScoreAdapter(
    val viewModel: StartGameViewModel
) : RecyclerView.Adapter<ScoreAdapter.ViewHolder>(), BindableAdapter<GameEngine> {

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

        val teams = viewModel.getTeams()
        teams?.let {
            if (it.size > position) {
                val roundCount = viewModel.getRoundCount()
                val team = it[position]

                val isGameStarted = it[0].roundScores.isNotEmpty()
                val isRoundStarted = it[0].roundScores[roundCount] != null

                val scoresCount = if (isGameStarted) {
                    if (isRoundStarted) {
                        roundCount
                    } else {
                        roundCount - 1
                    }
                } else {
                    0
                }

                holder.bindData(team, scoresCount, itemCount)
            }
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

        fun bindData(team: Team, scoresCount: Int, itemCount: Int) {
            binding.textViewTeamName.text = "${team.name}"

            if (adapterPosition == itemCount - 1) {
                binding.containerScores.background =
                    App.getContext().getDrawable(R.drawable.shape_game_score_background)
            } else {
                binding.containerScores.background =
                    App.getContext().getDrawable(R.drawable.layer_list_game_score_vertical_divider)
            }

            if (scoresCount != 0) {

                var text = ""
                for (i in 1..scoresCount) {

                    val score = team.roundScores[i]

                    if (score == null) {
                        text += "-"
                    } else {
                        text += score
                    }

                    if (i != scoresCount) {
                        text += "\n"
                    }
                }

                binding.textViewScore.text = text
//                binding.textViewScore.visibility = View.VISIBLE
            } else {
                binding.textViewScore.text = ""
//                binding.textViewScore.visibility = View.INVISIBLE
            }

            binding.textViewScore.text =
                "5\n5\n5\n5\n5\n5\n5\n5\n5\n5\n5\n5\n5\n5\n5\n5\n5\n5\n5\n5\n5\n5\n5\n5\n5\n5\n5\n5\n5\n5\n5\n5\n5\n5\n5\n5\n5\n5\n5\n5\n3"

            binding.textViewTotalScore.text = "${team.totalScore}"
        }
    }
}