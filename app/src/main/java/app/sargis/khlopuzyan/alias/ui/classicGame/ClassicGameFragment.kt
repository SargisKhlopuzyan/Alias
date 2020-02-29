package app.sargis.khlopuzyan.alias.ui.classicGame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import app.sargis.khlopuzyan.alias.R
import app.sargis.khlopuzyan.alias.databinding.FragmentClassicGameBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ClassicGameFragment : DaggerFragment() {

    companion object {
        fun newInstance() = ClassicGameFragment()
    }

    @Inject
    lateinit var viewModel: ClassicGameViewModel

    private lateinit var binding: FragmentClassicGameBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_classic_game, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.closeLiveData.observe(viewLifecycleOwner) {
            showFinishGameDialog()
        }

        viewModel.skipLiveData.observe(viewLifecycleOwner) {
            showFinishGameDialog()
        }
    }

    private fun showFinishGameDialog() {

    }

}
