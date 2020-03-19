package app.sargis.khlopuzyan.alias.di.module

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.sargis.khlopuzyan.alias.database.TeamNamesDatabaseManager
import app.sargis.khlopuzyan.alias.database.WordsDatabaseManager
import app.sargis.khlopuzyan.alias.di.factory.AppViewModelFactory
import app.sargis.khlopuzyan.alias.repository.DefaultSettingsRepository
import app.sargis.khlopuzyan.alias.repository.DefaultSettingsRepositoryImpl
import app.sargis.khlopuzyan.alias.repository.GameSetupRepository
import app.sargis.khlopuzyan.alias.repository.GameSetupRepositoryImpl
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
        fun provideTeamNamesDatabaseManager(
            context: Context
        ): TeamNamesDatabaseManager = TeamNamesDatabaseManager(context)

        @Provides
        @Singleton
        fun provideWordsDatabaseManager(
            context: Context
        ): WordsDatabaseManager = WordsDatabaseManager(context)

        @Provides
        @Singleton
        fun provideSharedPrefManager(
            context: Context
        ): SharedPrefManager = SharedPrefManager(context)

        @Provides
        @Singleton
        fun provideSettingsRepository(
            databaseManager: TeamNamesDatabaseManager,
            wordsDatabaseManager: WordsDatabaseManager,
            sharedPrefManager: SharedPrefManager
        ): DefaultSettingsRepository =
            DefaultSettingsRepositoryImpl(
                databaseManager,
                wordsDatabaseManager,
                sharedPrefManager
            )

        @Provides
        @Singleton
        fun provideGameSetupRepository(
            sharedPrefManager: SharedPrefManager,
            teamNamesDatabaseManager: TeamNamesDatabaseManager,
            wordsDatabaseManager: WordsDatabaseManager
        ): GameSetupRepository =
            GameSetupRepositoryImpl(
                sharedPrefManager,
                teamNamesDatabaseManager,
                wordsDatabaseManager
            )
    }
}