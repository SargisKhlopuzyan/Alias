package app.sargis.khlopuzyan.alias.ui.teams

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import app.sargis.khlopuzyan.alias.R
import app.sargis.khlopuzyan.alias.databinding.FragmentTeamsBinding
import app.sargis.khlopuzyan.alias.model.Team
import app.sargis.khlopuzyan.alias.ui.changeTeamNameDialog.ChangeTeamNameDialogFragment
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class TeamsFragment : DaggerFragment(), ChangeTeamNameDialogFragment.ChangeTeamNameDialogListener {

    companion object {
        fun newInstance(listener: GameTeamsChangeListener) = run {
            val teamsFragment = TeamsFragment()
            teamsFragment.gameTeamsChangeListener = listener
            teamsFragment
        }
    }

    interface GameTeamsChangeListener {
        fun addTeamName(team: Team)
        fun removeTeam(team: Team)
        fun changeTeamName(oldName: String, newName: String)
    }

    @Inject
    lateinit var viewModel: TeamsViewModel

    private lateinit var binding: FragmentTeamsBinding
    private lateinit var gameTeamsChangeListener: GameTeamsChangeListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_teams, container, false)

        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel

        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(context)

        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.hasFixedSize()
        val adapter = TeamsAdapter(viewModel)
        binding.recyclerView.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.changeTeamCLiveData.observe(viewLifecycleOwner) {
            showChangeTeamNameDialog(it)
        }

        viewModel.gameTeamsChangeListener = gameTeamsChangeListener

    }

    private fun showChangeTeamNameDialog(team: Team) {

        val fragmentTransaction = childFragmentManager?.beginTransaction()
        val prev = childFragmentManager.findFragmentByTag("changeTeamNameDialog")
        if (prev != null) {
            fragmentTransaction.remove(prev)
        }
        fragmentTransaction.addToBackStack(null)
        val dialogFragment =
            ChangeTeamNameDialogFragment.newInstance(
                team, this
            )
        dialogFragment.show(fragmentTransaction, "changeTeamNameDialog")
    }

    override fun onTeamNameChanged(oldName: String?, newName: String) {
        Log.e("LOG_TAG", "oldName: $oldName | newName: $newName")
    }

}