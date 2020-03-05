package app.sargis.khlopuzyan.alias.ui.game

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import app.sargis.khlopuzyan.alias.App
import app.sargis.khlopuzyan.alias.R
import app.sargis.khlopuzyan.alias.databinding.LayoutRecyclerViewItemGameWordBinding
import app.sargis.khlopuzyan.alias.model.Game
import app.sargis.khlopuzyan.alias.model.GameType
import app.sargis.khlopuzyan.alias.model.Language
import app.sargis.khlopuzyan.alias.model.Word
import app.sargis.khlopuzyan.alias.ui.common.BindableAdapter
import java.util.*


/**
 * Created by Sargis Khlopuzyan, on 2/28/2020.
 *
 * @author Sargis Khlopuzyan (sargis.khlopuzyan@fcc.am)
 */
class GameAdapter(
    val viewModel: GameViewModel
) : RecyclerView.Adapter<GameAdapter.ViewHolder>(), BindableAdapter<Game> {

    private var game = Game()

    private var words = mutableListOf<Word>()
    private var usedWords = mutableListOf<Word>()

    init {
        generateRandomWordsList()
    }

    fun getUsedWords() = usedWords

    private fun generateRandomWordsList() {

        words.clear()
        val wordsCount = if (game.gameType == GameType.Classic) 5 else 1

        if (game.currentPlayingTeam?.words.isNullOrEmpty()) {
            return
        }

        val rand = Random()

        for (i in 0 until wordsCount) {

            if (game.currentPlayingTeam?.words.isNullOrEmpty()) {
                break
            }

            game.currentPlayingTeam?.words?.let {

                val word: Word = it[rand.nextInt(it.size)]
                game.currentPlayingTeam?.words?.remove(word)

                words.add(word)
                usedWords.add(word)
            }
        }

        notifyDataSetChanged()
    }

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
        if (words.size > position) {
            holder.bindData(game, words[position], viewModel)
        }
    }

    override fun setItem(data: Game?) {
        data?.let {
            game = it
            generateRandomWordsList()
        }
    }

    fun skipWords() {
        Log.e("LOG_TAG", "skipWords")
    }

    class ViewHolder(binding: LayoutRecyclerViewItemGameWordBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var binding: LayoutRecyclerViewItemGameWordBinding = binding

        fun bindData(game: Game, word: Word, viewModel: GameViewModel) {
            binding.viewModel = viewModel
            binding.textViewWordName.text = getWord(word, game.settings.gameWordLanguage)

            setItemViewBackground(word)

            if (game.settings.isWordTranslateEnabled) {
                binding.textViewWordTranslateName.text =
                    getWord(word, game.settings.gameWordLanguage)
            }

            itemView.setOnClickListener {
                word.isGuessed = !word.isGuessed
                setItemViewBackground(word)

            }
        }

        private fun setItemViewBackground(word: Word) {
            if (word.isGuessed) {
                binding.constraintLayoutContainer.background = App.getContext().getDrawable(R.drawable.layer_list_background_gray_divider_gray)
            } else {
                binding.constraintLayoutContainer.background = App.getContext().getDrawable(R.drawable.layer_list_background_white_divider_grey)
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