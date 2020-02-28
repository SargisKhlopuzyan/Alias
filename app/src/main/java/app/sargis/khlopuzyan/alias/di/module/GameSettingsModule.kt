package app.sargis.khlopuzyan.alias.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.sargis.khlopuzyan.alias.di.annotation.ViewModelKey
import app.sargis.khlopuzyan.alias.repository.GameSettingsRepository
import app.sargis.khlopuzyan.alias.repository.GameSetupRepository
import app.sargis.khlopuzyan.alias.ui.gameSettings.GameSettingsFragment
import app.sargis.khlopuzyan.alias.ui.gameSettings.GameSettingsViewModel
import app.sargis.khlopuzyan.alias.ui.gameSetup.GameSetupFragment
import app.sargis.khlopuzyan.alias.ui.gameSetup.GameSetupViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * Created by Sargis Khlopuzyan, on 2/14/2020.
 *
 * @author Sargis Khlopuzyan (sargis.khlopuzyan@fcc.am)
 */

@Module(includes = [GameSettingsModule.ProvideViewModel::class])
interface GameSettingsModule {

    @ContributesAndroidInjector(modules = [InjectViewModel::class])
    fun bind(): GameSettingsFragment

    @Module
    class InjectViewModel {
        @Provides
        fun provideGameSettingsViewModel(
            factory: ViewModelProvider.Factory,
            target: GameSettingsFragment
        ) = ViewModelProvider(target, factory)[GameSettingsViewModel::class.java]
    }

    @Module
    class ProvideViewModel {

        @Provides
        @IntoMap
        @ViewModelKey(GameSettingsViewModel::class)
        fun provideGameSettingsViewModel(
            gameSettingsRepository: GameSettingsRepository
        ): ViewModel = GameSettingsViewModel(gameSettingsRepository)

    }
}