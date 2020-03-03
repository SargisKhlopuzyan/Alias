package app.sargis.khlopuzyan.alias.ui.gameSettings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import app.sargis.khlopuzyan.alias.R
import app.sargis.khlopuzyan.alias.databinding.FragmentGameSettingsBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class GameSettingsFragment : DaggerFragment() {

    companion object {
        fun newInstance(listener: GameSettingsChangedListener) = run {
            val teamsFragment = GameSettingsFragment()
            teamsFragment.gameSettingsChangedListener = listener
            teamsFragment
        }
    }

    interface GameSettingsChangedListener {
        fun setNumberOfWords(numberOfWords: Int)
        fun setRoundTime(roundTime: Int)
        fun setGameSoundState(isEnabled: Boolean)
        fun setMissedWordPenaltyState(isEnabled: Boolean)
        fun setGameWordsLanguage(language: String)
        fun setTranslateEnabledState(isEnabled: Boolean)
        fun setTranslateLanguage(language: String)
    }

    @Inject
    lateinit var viewModel: GameSettingsViewModel

    private lateinit var binding: FragmentGameSettingsBinding
    private lateinit var gameSettingsChangedListener: GameSettingsChangedListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_game_settings, container, false)

        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.gameSettingsChangedListener = gameSettingsChangedListener
    }
}