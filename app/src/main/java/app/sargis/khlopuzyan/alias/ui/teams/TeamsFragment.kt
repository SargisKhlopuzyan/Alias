package app.sargis.khlopuzyan.alias.ui.teams

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import app.sargis.khlopuzyan.alias.R
import app.sargis.khlopuzyan.alias.databinding.FragmentTeamsBinding
import app.sargis.khlopuzyan.alias.model.TeamName
import app.sargis.khlopuzyan.alias.ui.gameSettings.GameSettingsFragment
import app.sargis.khlopuzyan.alias.ui.gameSetup.GameSetupFragment
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class TeamsFragment : DaggerFragment() {

    companion object {
        fun newInstance(listener: GameTeamsChangeListener) = run {
            val teamsFragment = TeamsFragment()
            teamsFragment.gameTeamsChangeListener = listener
            teamsFragment
        }

    }

    interface GameTeamsChangeListener {
        fun addTeamName(teamName: TeamName)
        fun removeTeam(teamName: TeamName)
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
        viewModel.changeTeamNameCLiveData.observe(viewLifecycleOwner) {
            Log.e("LOG_TAG", "Change Team Name: $it")
            showRenameDialog()
        }

        viewModel.gameTeamsChangeListener = gameTeamsChangeListener

    }

    private fun showRenameDialog() {
//        val dialogFragment: MyDialogFragment = MyDialogFragment()
//        val ft: FragmentTransaction = fragmentManager?.beginTransaction();
//        var prev: Fragment? = getSupportFragmentManager().findFragmentByTag("dialog");
//            prev?.let {
//                ft.remove(prev);
//            }
//        ft.addToBackStack(null);
//        dialogFragment.show(ft, "dialog");
    }

}