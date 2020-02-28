package app.sargis.khlopuzyan.alias.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.sargis.khlopuzyan.alias.di.annotation.ViewModelKey
import app.sargis.khlopuzyan.alias.repository.GameSetupRepository
import app.sargis.khlopuzyan.alias.repository.TeamsRepository
import app.sargis.khlopuzyan.alias.ui.gameSetup.GameSetupFragment
import app.sargis.khlopuzyan.alias.ui.gameSetup.GameSetupViewModel
import app.sargis.khlopuzyan.alias.ui.teams.TeamsFragment
import app.sargis.khlopuzyan.alias.ui.teams.TeamsViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * Created by Sargis Khlopuzyan, on 2/14/2020.
 *
 * @author Sargis Khlopuzyan (sargis.khlopuzyan@fcc.am)
 */

@Module(includes = [TeamsModule.ProvideViewModel::class])
interface TeamsModule {

    @ContributesAndroidInjector(modules = [InjectViewModel::class])
    fun bind(): TeamsFragment

    @Module
    class InjectViewModel {
        @Provides
        fun provideTeamsViewModel(
            factory: ViewModelProvider.Factory,
            target: TeamsFragment
        ) = ViewModelProvider(target, factory)[TeamsViewModel::class.java]
    }

    @Module
    class ProvideViewModel {

        @Provides
        @IntoMap
        @ViewModelKey(TeamsViewModel::class)
        fun provideTeamsViewModel(
            teamsRepository: TeamsRepository
        ): ViewModel = TeamsViewModel(teamsRepository)

    }
}