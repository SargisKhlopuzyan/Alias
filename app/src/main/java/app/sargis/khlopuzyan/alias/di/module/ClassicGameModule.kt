package app.sargis.khlopuzyan.alias.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.sargis.khlopuzyan.alias.di.annotation.ViewModelKey
import app.sargis.khlopuzyan.alias.ui.classicGame.ClassicGameFragment
import app.sargis.khlopuzyan.alias.ui.classicGame.ClassicGameViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * Created by Sargis Khlopuzyan, on 2/14/2020.
 *
 * @author Sargis Khlopuzyan (sargis.khlopuzyan@fcc.am)
 */

@Module(includes = [ClassicGameModule.ProvideViewModel::class])
interface ClassicGameModule {

    @ContributesAndroidInjector(modules = [InjectViewModel::class])
    fun bind(): ClassicGameFragment

    @Module
    class InjectViewModel {
        @Provides
        fun provideClassicGameViewModel(
            factory: ViewModelProvider.Factory,
            target: ClassicGameFragment
        ) = ViewModelProvider(target, factory)[ClassicGameViewModel::class.java]
    }

    @Module
    class ProvideViewModel {

        @Provides
        @IntoMap
        @ViewModelKey(ClassicGameViewModel::class)
        fun provideClassicGameViewModel(
//            classicGameRepository: ClassicGameRepository
        ): ViewModel = ClassicGameViewModel(/*classicGameRepository*/)

    }
}