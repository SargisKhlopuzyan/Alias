package app.sargis.khlopuzyan.alias.di.module

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.sargis.khlopuzyan.alias.database.DatabaseManager
import app.sargis.khlopuzyan.alias.di.factory.AppViewModelFactory
import app.sargis.khlopuzyan.alias.repository.*
import app.sargis.khlopuzyan.alias.sharedPref.SharedPrefManager
import dagger.Module
import dagger.Provides
import javax.inject.Provider
import javax.inject.Singleton

/**
 * Created by Sargis Khlopuzyan, on 2/14/2020.
 *
 * @author Sargis Khlopuzyan (sargis.khlopuzyan@fcc.am)
 */
@Module(includes = [AppModule.ProvideViewModel::class])
abstract class AppModule {

    @Module
    class ProvideViewModel {
        @Provides
        fun provideViewModelFactory(
            providers: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
        ): ViewModelProvider.Factory = AppViewModelFactory(providers)

        @Provides
        @Singleton
        fun provideDatabaseManager(
            context: Context
        ): DatabaseManager = DatabaseManager(context)

        @Provides
        @Singleton
        fun provideSharedPrefManager(
            context: Context
        ): SharedPrefManager = SharedPrefManager(context)

        @Provides
        @Singleton
        fun provideSettingsRepository(
            sharedPrefManager: SharedPrefManager
        ): SettingsRepository =
            SettingsRepositoryImpl(
                sharedPrefManager
            )

        @Provides
        @Singleton
        fun provideGameSetupRepository(
            sharedPrefManager: SharedPrefManager
        ): GameSetupRepository =
            GameSetupRepositoryImpl(
                sharedPrefManager
            )

        @Provides
        @Singleton
        fun provideGameSettingsRepository(
            sharedPrefManager: SharedPrefManager
        ): GameSettingsRepository =
            GameSettingsRepositoryImpl(
                sharedPrefManager
            )

        @Provides
        @Singleton
        fun provideTeamsRepository(
            sharedPrefManager: SharedPrefManager
        ): TeamsRepository =
            TeamsRepositoryImpl(
                sharedPrefManager
            )

    }

}