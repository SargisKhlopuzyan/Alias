package app.sargis.khlopuzyan.alias.ui.gameSetup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.commit
import androidx.lifecycle.observe
import app.sargis.khlopuzyan.alias.R
import app.sargis.khlopuzyan.alias.databinding.FragmentGameSetupBinding
import app.sargis.khlopuzyan.alias.model.GameType
import app.sargis.khlopuzyan.alias.ui.startGame.StartGameFragment
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class GameSetupFragment : DaggerFragment() {

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
        gameSetupPagerAdapter = GameSetupPagerAdapter(childFragmentManager)
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
}
