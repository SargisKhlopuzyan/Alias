package app.sargis.khlopuzyan.alias.ui.main

import android.net.Uri
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
import app.sargis.khlopuzyan.alias.databinding.FragmentMainBinding
import app.sargis.khlopuzyan.alias.ui.selectGameType.SelectGameTypeFragment
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class MainFragment : DaggerFragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    @Inject
    lateinit var viewModel: MainViewModel

    private lateinit var binding: FragmentMainBinding

    private var imageUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)

        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel
        setupNavigationDrawer()
        setupObservers()
    }

    private fun setupNavigationDrawer() {
        binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    private fun setupObservers() {
        viewModel.openSettingsLiveData.observe(viewLifecycleOwner) {
            openSettings()
        }

        viewModel.newGameLiveData.observe(viewLifecycleOwner) {
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
                SelectGameTypeFragment.newInstance(),
                "fragment_select_game_type"
            )
            addToBackStack("select_game_type")
        }
    }

}