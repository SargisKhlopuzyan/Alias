package app.sargis.khlopuzyan.alias.ui.game

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import app.sargis.khlopuzyan.alias.R
import app.sargis.khlopuzyan.alias.databinding.LayoutRecyclerViewItemGameWordBinding
import app.sargis.khlopuzyan.alias.model.Game
import app.sargis.khlopuzyan.alias.model.GameType
import app.sargis.khlopuzyan.alias.ui.common.BindableAdapter

/**
 * Created by Sargis Khlopuzyan, on 2/28/2020.
 *
 * @author Sargis Khlopuzyan (sargis.khlopuzyan@fcc.am)
 */
class GameAdapter(
    val viewModel: GameViewModel
) : RecyclerView.Adapter<GameAdapter.ViewHolder>(), BindableAdapter<Game> {

    private var game = Game()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: LayoutRecyclerViewItemGameWordBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.layout_recycler_view_item_game_word,
            parent, false
        )
        return ViewHolder(
            binding
        )
    }

    override fun getItemCount(): Int {
        return if (game.gameType == GameType.Classic) 5
        else 1
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(game, viewModel)
    }

    override fun setItem(data: Game?) {
        data?.let {
            game = it
        }
    }

    class ViewHolder(binding: LayoutRecyclerViewItemGameWordBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var binding: LayoutRecyclerViewItemGameWordBinding = binding

        fun bindData(game: Game, viewModel: GameViewModel) {
            binding.viewModel = viewModel
//            binding.textViewName.text = game.teamName
        }
    }

}