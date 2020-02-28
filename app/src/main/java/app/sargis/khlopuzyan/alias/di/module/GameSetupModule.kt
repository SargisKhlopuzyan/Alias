package app.sargis.khlopuzyan.alias.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.sargis.khlopuzyan.alias.di.annotation.ViewModelKey
import app.sargis.khlopuzyan.alias.repository.GameSetupRepository
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

@Module(includes = [GameSetupModule.ProvideViewModel::class])
interface GameSetupModule {

    @ContributesAndroidInjector(modules = [InjectViewModel::class])
    fun bind(): GameSetupFragment

    @Module
    class InjectViewModel {
        @Provides
        fun provideGameSetupViewModel(
            factory: ViewModelProvider.Factory,
            target: GameSetupFragment
        ) = ViewModelProvider(target, factory)[GameSetupViewModel::class.java]
    }

    @Module
    class ProvideViewModel {

        @Provides
        @IntoMap
        @ViewModelKey(GameSetupViewModel::class)
        fun provideGameSetupViewModel(
            gameSetupRepository: GameSetupRepository
        ): ViewModel = GameSetupViewModel(gameSetupRepository)

    }
}