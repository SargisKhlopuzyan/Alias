package app.sargis.khlopuzyan.alias.ui.gameSetup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import app.sargis.khlopuzyan.alias.R
import app.sargis.khlopuzyan.alias.databinding.FragmentGameSetupBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class GameSetupFragment : DaggerFragment() {

    companion object {
        fun newInstance() = GameSetupFragment()
    }

    private lateinit var binding: FragmentGameSetupBinding

    private lateinit var gameSetupPagerAdapter: GameSetupPagerAdapter

    @Inject
    lateinit var viewModel: GameSetupViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game_setup, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        gameSetupPagerAdapter = GameSetupPagerAdapter(childFragmentManager)
        binding.viewPager.adapter = gameSetupPagerAdapter
        binding.viewPager.setCurrentItem(1, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel
        setupObservers()
    }

    private fun setupObservers() {
//        viewModel.openSettingsLiveData.observe(viewLifecycleOwner) {
//            openSettings()
//        }
//
//        viewModel.newGameLiveData.observe(viewLifecycleOwner) {
//            openNewGameFragment()
//        }
    }

}
