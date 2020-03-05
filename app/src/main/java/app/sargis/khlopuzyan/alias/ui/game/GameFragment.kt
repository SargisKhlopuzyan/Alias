package app.sargis.khlopuzyan.alias.ui.game

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import app.sargis.khlopuzyan.alias.R
import app.sargis.khlopuzyan.alias.databinding.FragmentGameBinding
import app.sargis.khlopuzyan.alias.model.Game
import app.sargis.khlopuzyan.alias.ui.common.OnBackPressed
import app.sargis.khlopuzyan.alias.ui.startGame.StartGameFragment
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class GameFragment : DaggerFragment(), OnBackPressed {

    companion object {

        private const val ARG_GAME = "arg_game"

        fun newInstance(game: Game?) = GameFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARG_GAME, game)
            }
        }
    }

    @Inject
    lateinit var viewModel: GameViewModel

    private lateinit var binding: FragmentGameBinding


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

        binding.viewModel = viewModel

        val game: Game? = arguments?.getParcelable(ARG_GAME)
        game?.let {
            viewModel.setGame(it)
        }

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
            showFinishGameDialog()
        }

        viewModel.roundFinishedLiveData.observe(viewLifecycleOwner) {
            finishRound(it)
        }

        viewModel.skipLiveData.observe(viewLifecycleOwner) {
            skipWords()
        }
    }

    private fun finishRound(game: Game) {
        val invoker = targetFragment
        if (invoker is StartGameFragment) {
            invoker.handleGameRoundResult(game)
            parentFragmentManager.popBackStack("game", FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }

    private fun showFinishGameDialog() {
        println("${viewModel.gameLiveData.value?.gameType}")
    }

    private fun skipWords() {
        val adapter = binding.recyclerView.adapter
        if (adapter is GameAdapter) {
            adapter.skipWords()
        }

        println("${viewModel.gameLiveData.value?.gameType}")
    }

    override fun onBackPressed() {
        Log.e("LOG_TAG", "onBackPressed")
        activity?.supportFragmentManager?.popBackStack()
    }
}
