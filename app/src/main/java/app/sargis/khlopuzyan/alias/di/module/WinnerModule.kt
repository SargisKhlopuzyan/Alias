package app.sargis.khlopuzyan.alias.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.sargis.khlopuzyan.alias.di.annotation.ViewModelKey
import app.sargis.khlopuzyan.alias.ui.winner.WinnerFragment
import app.sargis.khlopuzyan.alias.ui.winner.WinnerViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * Created by Sargis Khlopuzyan, on 2/14/2020.
 *
 * @author Sargis Khlopuzyan (sargis.khlopuzyan@fcc.am)
 */

@Module(includes = [WinnerModule.ProvideViewModel::class])
interface WinnerModule {

    @ContributesAndroidInjector(modules = [InjectViewModel::class])
    fun bind(): WinnerFragment

    @Module
    class InjectViewModel {
        @Provides
        fun provideWinnerViewModel(
            factory: ViewModelProvider.Factory,
            target: WinnerFragment
        ) = ViewModelProvider(target, factory)[WinnerViewModel::class.java]
    }

    @Module
    class ProvideViewModel {
        @Provides
        @IntoMap
        @ViewModelKey(WinnerViewModel::class)
        fun provideWinnerViewModel(
        ): ViewModel = WinnerViewModel()

    }
}