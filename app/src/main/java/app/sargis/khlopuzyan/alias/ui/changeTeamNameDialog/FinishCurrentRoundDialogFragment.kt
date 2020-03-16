package app.sargis.khlopuzyan.alias.ui.changeTeamNameDialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import app.sargis.khlopuzyan.alias.R
import app.sargis.khlopuzyan.alias.databinding.DialogFragmentFinishCurrentRoundBinding
import app.sargis.khlopuzyan.alias.model.Team

class FinishCurrentRoundDialogFragment private constructor(
    val team: Team,
    private var finishCurrentRoundDialogListener: FinishCurrentRoundDialogListener
) : DialogFragment() {

    companion object {
        fun newInstance(
            team: Team,
            finishCurrentRoundDialogListener: FinishCurrentRoundDialogListener
        ) =
            FinishCurrentRoundDialogFragment(
                team,
                finishCurrentRoundDialogListener
            )
    }

    interface FinishCurrentRoundDialogListener {
        fun onFinishCurrentRound(team: Team)
    }

    private lateinit var binding: DialogFragmentFinishCurrentRoundBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.dialog_fragment_finish_current_round,
                container,
                false
            )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupObservers()
    }

    private fun setupObservers() {
        binding.onCancelClickListener = View.OnClickListener { dismiss() }

        binding.onOkClickListener = View.OnClickListener {
            finishCurrentRoundDialogListener.onFinishCurrentRound(team)
            dismiss()
        }
    }

}
