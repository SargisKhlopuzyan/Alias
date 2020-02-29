package app.sargis.khlopuzyan.alias.ui.startGame

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.commit
import androidx.lifecycle.observe
import app.sargis.khlopuzyan.alias.R
import app.sargis.khlopuzyan.alias.databinding.FragmentStartGameBinding
import app.sargis.khlopuzyan.alias.model.Game
import app.sargis.khlopuzyan.alias.ui.game.GameFragment
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class StartGameFragment : DaggerFragment() {

    companion object {

        private const val ARG_GAME = "arg_game"

        fun newInstance(game: Game?) = StartGameFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARG_GAME, game)
            }
        }
    }

    @Inject
    lateinit var viewModel: StartGameViewModel

    private lateinit var binding: FragmentStartGameBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_start_game, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel
        val game: Game? = arguments?.getParcelable(ARG_GAME)
        viewModel.game.value = game
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.showScoreLiveData.observe(viewLifecycleOwner) {
            showScore()
        }

        viewModel.startLiveData.observe(viewLifecycleOwner) {
            startGameFragment()
        }
    }

    private fun showScore() {
        binding.drawerLayout.openDrawer(Gravity.LEFT);
    }

    private fun startGameFragment() {
        activity?.supportFragmentManager?.commit {
            replace(
                android.R.id.content,
                GameFragment.newInstance(viewModel.game.value),
                "fragment_game"
            )
            addToBackStack("game")
        }
    }

}