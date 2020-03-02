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
import app.sargis.khlopuzyan.alias.model.GameType
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

        viewModel.startClassGameLiveData.observe(viewLifecycleOwner) {
            startClassGame()
        }

        viewModel.startArcadeGameLiveData.observe(viewLifecycleOwner) {
            startArcadeGame()
        }
    }

    private fun startClassGame() {
        startGame(GameType.Classic)
    }

    private fun startArcadeGame() {
        startGame(GameType.Arcade)
    }

    private fun startGame(gameType: GameType) {

        viewModel.game.value?.let {
            it.gameType = gameType
        }

        activity?.supportFragmentManager?.commit {
            replace(
                android.R.id.content,
                StartGameFragment.newInstance(viewModel.game.value),
                "fragment_start_game"
            )
            addToBackStack("start_game")
        }
    }


    //

    override fun setNumberOfWords(numberOfWords: Int) {
        Log.e("LOG_TAG", "setNumberOfWords")
    }

    override fun setRoundTime(roundTime: Int) {
        Log.e("LOG_TAG", "setRoundTime")
    }

    override fun setGameSoundState(isEnabled: Boolean) {
        Log.e("LOG_TAG", "setGameSoundState")
    }

    override fun setMissedWordPenaltyState(isEnabled: Boolean) {
        Log.e("LOG_TAG", "setMissedWordPenaltyState")
    }

    override fun setGameWordsLanguage(language: String) {
        Log.e("LOG_TAG", "setGameWordsLanguage")
    }

    override fun setTranslateEnabledState(isEnabled: Boolean) {
        Log.e("LOG_TAG", "setTranslateEnabledState")
    }

    override fun setTranslateLanguage(language: String) {
        Log.e("LOG_TAG", "setTranslateLanguage")
    }

    //

    override fun addTeamName(team: Team) {
        Log.e("LOG_TAG", "addTeamName")
    }

    override fun removeTeam(team: Team) {
        Log.e("LOG_TAG", "removeTeam")
    }

    override fun changeTeamName(oldName: String, newName: String) {
        Log.e("LOG_TAG", "changeTeamName")
    }
}
