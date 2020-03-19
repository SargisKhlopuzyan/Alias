package app.sargis.khlopuzyan.alias.ui.game

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import app.sargis.khlopuzyan.alias.App
import app.sargis.khlopuzyan.alias.R
import app.sargis.khlopuzyan.alias.databinding.LayoutRecyclerViewItemGameWordBinding
import app.sargis.khlopuzyan.alias.game.GameEngine
import app.sargis.khlopuzyan.alias.model.GameType
import app.sargis.khlopuzyan.alias.model.Language
import app.sargis.khlopuzyan.alias.model.Word
import app.sargis.khlopuzyan.alias.ui.common.BindableAdapter


/**
 * Created by Sargis Khlopuzyan, on 2/28/2020.
 *
 * @author Sargis Khlopuzyan (sargis.khlopuzyan@fcc.am)
 */
class GameAdapter(
    val viewModel: GameViewModel
) : RecyclerView.Adapter<GameAdapter.ViewHolder>(), BindableAdapter<GameEngine> {

    init {
        viewModel.generateRandomWordsList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: LayoutRecyclerViewItemGameWordBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.layout_recycler_view_item_game_word,
            parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (viewModel.getGameType() == GameType.Classic) 5
        else 1
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (viewModel.words.size > position) {
            holder.bindData(viewModel.words[position], viewModel)
        }
    }

    override fun setItem(data: GameEngine?) {
        data?.let {
            notifyDataSetChanged()
        }
    }

    class ViewHolder(
        var binding: LayoutRecyclerViewItemGameWordBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(word: Word, viewModel: GameViewModel) {
            binding.viewModel = viewModel

            setItemViewBackground(word)


            binding.textViewWordName.text = getWord(word, viewModel.getGameWordLanguage())


            if (viewModel.isWordTranslateEnabled()) {
                binding.textViewWordTranslateName.text =
                    getWord(word, viewModel.getGameWordLanguage())
            }

            itemView.setOnClickListener {
                word.isGuessed = !word.isGuessed
                viewModel.handleWordGuessed(word)
                setItemViewBackground(word)

                viewModel.wordGuessedStateChanged(word.isGuessed)
            }
        }

        private fun setItemViewBackground(word: Word) {
            if (word.isGuessed) {
                binding.constraintLayoutContainer.background =
                    App.getContext().getDrawable(R.drawable.layer_list_background_gray_divider_gray)
            } else {
                binding.constraintLayoutContainer.background = App.getContext()
                    .getDrawable(R.drawable.layer_list_background_white_divider_grey)
            }
        }

        private fun getWord(word: Word, language: String): String? {
            return when (language) {
                Language.EN -> return word.wordEn
                Language.AM -> return word.wordAm
                Language.RU -> return word.wordRu
                else -> ""
            }
        }
    }

}