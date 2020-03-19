package app.sargis.khlopuzyan.alias.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.sargis.khlopuzyan.alias.di.annotation.ViewModelKey
import app.sargis.khlopuzyan.alias.ui.game.GameFragment
import app.sargis.khlopuzyan.alias.ui.game.GameViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * Created by Sargis Khlopuzyan, on 2/14/2020.
 *
 * @author Sargis Khlopuzyan (sargis.khlopuzyan@fcc.am)
 */

@Module(includes = [GameModule.ProvideViewModel::class])
interface GameModule {

    @ContributesAndroidInjector(modules = [InjectViewModel::class])
    fun bind(): GameFragment

    @Module
    class InjectViewModel {
        @Provides
        fun provideGameViewModel(
            factory: ViewModelProvider.Factory,
            target: GameFragment
        ) = ViewModelProvider(target, factory)[GameViewModel::class.java]
    }

    @Module
    class ProvideViewModel {

        @Provides
        @IntoMap
        @ViewModelKey(GameViewModel::class)
        fun provideGameViewModel(
        ): ViewModel = GameViewModel()

    }
}