package app.sargis.khlopuzyan.alias.ui.teams

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.commit
import androidx.lifecycle.observe
import app.sargis.khlopuzyan.alias.R
import app.sargis.khlopuzyan.alias.databinding.FragmentGameSettingsBinding
import app.sargis.khlopuzyan.alias.databinding.FragmentTeamsBinding
import app.sargis.khlopuzyan.alias.ui.gameSetup.GameSetupFragment
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class TeamsFragment : DaggerFragment() {

    companion object {
        fun newInstance() = TeamsFragment()
    }

    @Inject
    lateinit var viewModel: TeamsViewModel

    private lateinit var binding: FragmentTeamsBinding

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
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.openSettingsLiveData.observe(viewLifecycleOwner) {

        }

        viewModel.newGameLiveData.observe(viewLifecycleOwner) {
            openNewGameFragment()
        }
    }

    private fun openNewGameFragment() {
        activity?.supportFragmentManager?.commit {
            replace(
                android.R.id.content,
                GameSetupFragment.newInstance(),
                "fragment_select_game_type"
            )
            addToBackStack("select_game_type")
        }
    }

}