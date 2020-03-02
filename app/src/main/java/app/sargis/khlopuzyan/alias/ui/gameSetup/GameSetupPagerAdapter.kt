package app.sargis.khlopuzyan.alias.ui.gameSetup

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import app.sargis.khlopuzyan.alias.ui.gameSettings.GameSettingsFragment
import app.sargis.khlopuzyan.alias.ui.teams.TeamsFragment

/**
 * Created by Sargis Khlopuzyan, on 2/28/2020.
 *
 * @author Sargis Khlopuzyan (sargis.khlopuzyan@fcc.am)
 */
class GameSetupPagerAdapter(
    fm: FragmentManager,
    private var gameSettingsChangedListener: GameSettingsFragment.GameSettingsChangedListener,
    private var gameTeamsChangeListener: TeamsFragment.GameTeamsChangeListener
) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {

        when (position) {
            0 -> {
                return GameSettingsFragment.newInstance(gameSettingsChangedListener)
            }
            1 -> {
                return TeamsFragment.newInstance(gameTeamsChangeListener)
            }
        }
        throw (IllegalArgumentException())
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "Game Settings"
            1 -> return "Teams"
        }
        return super.getPageTitle(position)
    }
}