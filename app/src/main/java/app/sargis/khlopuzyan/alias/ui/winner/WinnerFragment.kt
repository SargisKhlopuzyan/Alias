package app.sargis.khlopuzyan.alias.ui.winner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import app.sargis.khlopuzyan.alias.R
import app.sargis.khlopuzyan.alias.databinding.FragmentWinnerBinding
import app.sargis.khlopuzyan.alias.gameEngine.GameEngine
import app.sargis.khlopuzyan.alias.ui.common.OnBackPressed
import app.sargis.khlopuzyan.alias.ui.startGame.StartGameFragment
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class WinnerFragment : DaggerFragment(), OnBackPressed {

    companion object {

        private const val ARG_GAME = "arg_game"

        fun newInstance(gameEngine: GameEngine) = WinnerFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARG_GAME, gameEngine)
            }
        }
    }

    @Inject
    lateinit var viewModel: WinnerViewModel

    private lateinit var binding: FragmentWinnerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_winner, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.viewModel = viewModel

        val gameEngine: GameEngine? = arguments?.getParcelable(ARG_GAME)
        viewModel.setupGameEngine(gameEngine)

        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        val adapter = WinnerAdapter(viewModel)
        binding.recyclerView.hasFixedSize()

        val layoutManager = LinearLayoutManager(context)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.startNewGameLiveData.observe(viewLifecycleOwner) {
            startNewGame()
        }
    }

    private fun startNewGame() {
        val invoker = targetFragment
        if (invoker is StartGameFragment) {
            invoker.handleWinnerResult()
            parentFragmentManager.popBackStack("winner", FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }

    override fun onBackPressed() {
        startNewGame()
    }
}
