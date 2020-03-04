package app.sargis.khlopuzyan.alias.ui.gameSetup

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.commit
import androidx.lifecycle.observe
import app.sargis.khlopuzyan.alias.R
import app.sargis.khlopuzyan.alias.databinding.FragmentGameSetupBinding
import app.sargis.khlopuzyan.alias.model.Game
import app.sargis.khlopuzyan.alias.model.Team
import app.sargis.khlopuzyan.alias.ui.gameSettings.GameSettingsFragment
import app.sargis.khlopuzyan.alias.ui.startGame.StartGameFragment
import app.sargis.khlopuzyan.alias.ui.teams.TeamsFragment
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class GameSetupFragment : DaggerFragment(), GameSettingsFragment.GameSettingsChangedListener,
    TeamsFragment.GameTeamsChangeListener {

    companion object {
        fun newInstance() = GameSetupFragment()
    }

    private lateinit var binding: FragmentGameSetupBinding

    private lateinit var gameSetupPagerAdapter: GameSetupPagerAdapter

    @Inject
    lateinit var viewModel: GameSetupViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game_setup, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        gameSetupPagerAdapter = GameSetupPagerAdapter(childFragmentManager, this, this)
        binding.viewPager.adapter = gameSetupPagerAdapter
        binding.viewPager.setCurrentItem(1, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.startGameLiveData.observe(viewLifecycleOwner) {
            startGame(it)
        }
    }

    private fun startGame(game: Game) {
        activity?.supportFragmentManager?.commit {
            replace(
                android.R.id.content,
                StartGameFragment.newInstance(game),
                "fragment_start_game"
            )
            addToBackStack("start_game")
        }
    }

    //

    override fun setNumberOfWords(numberOfWords: Int) {
        viewModel.setNumberOfWords(numberOfWords)
    }

    override fun setRoundTime(roundTime: Int) {
        viewModel.setRoundTime(roundTime)
    }

    override fun setGameSoundState(isEnabled: Boolean) {
        viewModel.setGameSoundState(isEnabled)
    }

    override fun setMissedWordPenaltyState(isEnabled: Boolean) {
        viewModel.setMissedWordPenaltyState(isEnabled)
    }

    override fun setGameWordsLanguage(language: String) {
        viewModel.setGameWordsLanguage(language)
    }

    override fun setTranslateEnabledState(isEnabled: Boolean) {
        viewModel.setTranslateEnabledState(isEnabled)
    }

    override fun setTranslateLanguage(language: String) {
        viewModel.setTranslateLanguage(language)
    }

    override fun setTeam(teams: List<Team>) {
        Log.e("LOG_TAG", "setTeam")
        viewModel.setTeam(teams)
    }

}
