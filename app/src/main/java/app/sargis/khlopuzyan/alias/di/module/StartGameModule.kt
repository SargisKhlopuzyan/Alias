package app.sargis.khlopuzyan.alias.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.sargis.khlopuzyan.alias.di.annotation.ViewModelKey
import app.sargis.khlopuzyan.alias.repository.StartGameRepository
import app.sargis.khlopuzyan.alias.ui.startGame.StartGameFragment
import app.sargis.khlopuzyan.alias.ui.startGame.StartGameViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * Created by Sargis Khlopuzyan, on 2/14/2020.
 *
 * @author Sargis Khlopuzyan (sargis.khlopuzyan@fcc.am)
 */

@Module(includes = [StartGameModule.ProvideViewModel::class])
interface StartGameModule {

    @ContributesAndroidInjector(modules = [InjectViewModel::class])
    fun bind(): StartGameFragment

    @Module
    class InjectViewModel {
        @Provides
        fun provideStartGameViewModel(
            factory: ViewModelProvider.Factory,
            target: StartGameFragment
        ) = ViewModelProvider(target, factory)[StartGameViewModel::class.java]
    }

    @Module
    class ProvideViewModel {

        @Provides
        @IntoMap
        @ViewModelKey(StartGameViewModel::class)
        fun provideStartGameViewModel(
            startGameRepository: StartGameRepository
        ): ViewModel = StartGameViewModel(startGameRepository)

    }
}