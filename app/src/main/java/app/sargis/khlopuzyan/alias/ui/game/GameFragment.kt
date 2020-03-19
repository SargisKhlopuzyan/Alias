package app.sargis.khlopuzyan.alias.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import app.sargis.khlopuzyan.alias.R
import app.sargis.khlopuzyan.alias.databinding.FragmentGameBinding
import app.sargis.khlopuzyan.alias.gameEngine.GameEngine
import app.sargis.khlopuzyan.alias.model.Team
import app.sargis.khlopuzyan.alias.ui.changeTeamNameDialog.FinishCurrentRoundDialogFragment
import app.sargis.khlopuzyan.alias.ui.common.OnBackPressed
import app.sargis.khlopuzyan.alias.ui.startGame.StartGameFragment
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class GameFragment : DaggerFragment(), OnBackPressed,
    FinishCurrentRoundDialogFragment.FinishCurrentRoundDialogListener {

    companion object {
        fun newInstance(gameEngine: GameEngine) = run {
            val gameFragment = GameFragment()
            gameFragment.gameEngine = gameEngine
            gameFragment
        }
    }

    @Inject
    lateinit var viewModel: GameViewModel

    private lateinit var binding: FragmentGameBinding

    private lateinit var gameEngine: GameEngine

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.setupGameEngine(gameEngine)
        binding.viewModel = viewModel

        setupRecyclerView()
        setupObservers()
    }


    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(context)

        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.hasFixedSize()
        val adapter = GameAdapter(viewModel)
        binding.recyclerView.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.closeLiveData.observe(viewLifecycleOwner) {
            onBackPressed()
        }

        viewModel.roundFinishedLiveData.observe(viewLifecycleOwner) {
            finishRound(it)
        }

    }

    private fun finishRound(gameEngine: GameEngine) {
        val invoker = targetFragment
        if (invoker is StartGameFragment) {
            invoker.handleGameRoundResult(gameEngine)
            parentFragmentManager.popBackStack("game", FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }

    override fun onBackPressed() {
        viewModel.gameEngineLiveData.value?.currentPlayingTeam?.let {
            showFinishCurrentRoundDialog(it)
        }
    }

    private fun showFinishCurrentRoundDialog(team: Team) {

        val fragmentTransaction = childFragmentManager.beginTransaction()
        val prev = childFragmentManager.findFragmentByTag("finishCurrentRoundDialog")
        if (prev != null) {
            fragmentTransaction.remove(prev)
        }
        fragmentTransaction.addToBackStack(null)
        val dialogFragment =
            FinishCurrentRoundDialogFragment.newInstance(
                team, this
            )
        dialogFragment.show(fragmentTransaction, "finishCurrentRoundDialog")
    }

    override fun onFinishCurrentRound(team: Team) {
        viewModel.finishRound()
    }
}
