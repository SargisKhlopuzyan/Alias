package app.sargis.khlopuzyan.alias.ui.changeTeamNameDialog

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import app.sargis.khlopuzyan.alias.R
import app.sargis.khlopuzyan.alias.databinding.DialogFragmentChangeTeamNameBinding
import app.sargis.khlopuzyan.alias.model.Team

class ChangeTeamNameDialogFragment private constructor(
    val team: Team,
    var changeTeamNameDialogListener: ChangeTeamNameDialogListener
) : DialogFragment() {

    companion object {
        fun newInstance(team: Team, changeTeamNameDialogListener: ChangeTeamNameDialogListener) =
            ChangeTeamNameDialogFragment(
                team,
                changeTeamNameDialogListener
            )
    }

    interface ChangeTeamNameDialogListener {
        fun onTeamNameChanged(oldName: String?, newName: String)
    }

    private lateinit var binding: DialogFragmentChangeTeamNameBinding

    private var teamName = ""
    private var isErrorVisible = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.dialog_fragment_change_team_name,
                container,
                false
            )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupObservers()
        binding.editTextTeamName.hint = team.name
    }

    private fun setupObservers() {

        binding.editTextTeamName.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {
                s.toString().let {
                    teamName = it
                    binding.textViewTeamNameLettersCount.text = "${it.length}/30"
                    hideErrorMessage()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        binding.onCancelClickListener = View.OnClickListener { dismiss() }

        binding.onOkClickListener = View.OnClickListener {
            if (teamName.isEmpty()) {
                showErrorMessage(getString(R.string.error_message_empty_team_name))
            } else {
                changeTeamNameDialogListener.onTeamNameChanged(team.name, teamName)
                dismiss()
            }
        }
    }

    private fun showErrorMessage(errorMessage: String) {
        isErrorVisible = true
        binding.textViewErrorMessage.text = errorMessage
        binding.editTextTeamName.background =
            resources.getDrawable(
                R.drawable.layer_edit_text_under_line_red_background,
                context?.theme
            )
    }

    private fun hideErrorMessage() {
        if (isErrorVisible) {
            isErrorVisible = false
            binding.textViewErrorMessage.text = ""
            binding.editTextTeamName.background =
                resources.getDrawable(
                    R.drawable.layer_edit_text_under_line_blue_background,
                    context?.theme
                )
        }
    }

}
