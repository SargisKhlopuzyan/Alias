package app.sargis.khlopuzyan.alias.ui.startGame

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.commit
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import app.sargis.khlopuzyan.alias.R
import app.sargis.khlopuzyan.alias.databinding.FragmentStartGameBinding
import app.sargis.khlopuzyan.alias.gameEngine.GameEngine
import app.sargis.khlopuzyan.alias.ui.game.GameFragment
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class StartGameFragment : DaggerFragment() {

    companion object {

        private const val ARG_GAME = "arg_game"
        private const val REQUEST_CODE_TARGET_FRAGMENT = 100

        fun newInstance(gameEngine: GameEngine) = StartGameFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARG_GAME, gameEngine)
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_start_game, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.viewModel = viewModel

        val gameEngine: GameEngine? = arguments?.getParcelable(ARG_GAME)
        gameEngine?.let {
            if (it.currentPlayingTeam == null) {
                viewModel.handleRoundFinish(it)
            }
        }
        viewModel.setupGameEngine(gameEngine)

        setupNavigationDrawer()
        setupRecyclerView()
        setupObservers()
    }

    private fun setupNavigationDrawer() {
        binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    private fun setupRecyclerView() {
        val adapter = ScoreAdapter(viewModel)
        binding.layoutNavScore.recyclerView.hasFixedSize()

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.layoutNavScore.recyclerView.layoutManager = layoutManager
        binding.layoutNavScore.recyclerView.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.showScoreLiveData.observe(viewLifecycleOwner) {
            showScore()
        }

        viewModel.startLiveData.observe(viewLifecycleOwner) {
            startGameFragment(it)
        }
    }

    private fun showScore() {
        binding.drawerLayout.openDrawer(Gravity.LEFT);
        binding.layoutNavScore.recyclerView.scrollToPosition(0)
        binding.layoutNavScore.recyclerView.smoothScrollToPosition(0)
    }

    private fun startGameFragment(gameEngine: GameEngine) {

        val gameFragment = GameFragment.newInstance(gameEngine)
        gameFragment.setTargetFragment(this@StartGameFragment, REQUEST_CODE_TARGET_FRAGMENT)

        activity?.supportFragmentManager?.commit {
            replace(
                android.R.id.content,
                gameFragment,
                "fragment_game"
            )
            addToBackStack("game")
        }
    }

    fun handleGameRoundResult(gameEngine: GameEngine) {
        viewModel.handleRoundFinish(gameEngine)
    }

}