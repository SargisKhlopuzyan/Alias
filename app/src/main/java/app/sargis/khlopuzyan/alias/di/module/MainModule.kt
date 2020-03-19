package app.sargis.khlopuzyan.alias.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.sargis.khlopuzyan.alias.di.annotation.ViewModelKey
import app.sargis.khlopuzyan.alias.repository.DefaultSettingsRepository
import app.sargis.khlopuzyan.alias.ui.main.MainAndDefaultSettingsFragment
import app.sargis.khlopuzyan.alias.ui.main.MainAndDefaultSettingsViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * Created by Sargis Khlopuzyan, on 2/14/2020.
 *
 * @author Sargis Khlopuzyan (sargis.khlopuzyan@fcc.am)
 */

@Module(includes = [MainModule.ProvideViewModel::class])
interface MainModule {

    @ContributesAndroidInjector(modules = [InjectViewModel::class])
    fun bind(): MainAndDefaultSettingsFragment

    @Module
    class InjectViewModel {
        @Provides
        fun provideMainViewModel(
            factory: ViewModelProvider.Factory,
            target: MainAndDefaultSettingsFragment
        ) = ViewModelProvider(target, factory)[MainAndDefaultSettingsViewModel::class.java]
    }

    @Module
    class ProvideViewModel {

        @Provides
        @IntoMap
        @ViewModelKey(MainAndDefaultSettingsViewModel::class)
        fun provideMainViewModel(
            defaultSettingsRepository: DefaultSettingsRepository
        ): ViewModel = MainAndDefaultSettingsViewModel(defaultSettingsRepository)

    }
}