package app.sargis.khlopuzyan.alias.ui.main

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
import app.sargis.khlopuzyan.alias.databinding.FragmentMainAndDefaultSettingsBinding
import app.sargis.khlopuzyan.alias.ui.gameSetup.GameSetupFragment
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class MainAndDefaultSettingsFragment : DaggerFragment() {

    companion object {
        fun newInstance() = MainAndDefaultSettingsFragment()
    }

    @Inject
    lateinit var mainAndDefaultSettingsViewModel: MainAndDefaultSettingsViewModel

    private lateinit var binding: FragmentMainAndDefaultSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_main_and_default_settings,
                container,
                false
            )

        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = mainAndDefaultSettingsViewModel
        setupNavigationDrawer()
        setupObservers()

        mainAndDefaultSettingsViewModel.storeDefaultTeamNames()
    }

    private fun setupNavigationDrawer() {
        binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    private fun setupObservers() {
        mainAndDefaultSettingsViewModel.openSettingsLiveData.observe(viewLifecycleOwner) {
            openSettings()
        }

        mainAndDefaultSettingsViewModel.newGameLiveData.observe(viewLifecycleOwner) {
            openNewGameFragment()
        }
    }

    private fun openSettings() {
        binding.drawerLayout.openDrawer(Gravity.LEFT);
    }

    private fun openNewGameFragment() {
        activity?.supportFragmentManager?.commit {
            replace(
                android.R.id.content,
                GameSetupFragment.newInstance(),
                "fragment_game_setup"
            )
            addToBackStack("game_setup")
        }
    }

}