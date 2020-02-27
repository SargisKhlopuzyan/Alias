package app.sargis.khlopuzyan.alias.ui.selectGameType

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import app.sargis.khlopuzyan.alias.R

class SelectGameTypeFragment : Fragment() {

    companion object {
        fun newInstance() = SelectGameTypeFragment()
    }

    private lateinit var viewModel: SelectGameTypeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_select_game_type, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SelectGameTypeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
