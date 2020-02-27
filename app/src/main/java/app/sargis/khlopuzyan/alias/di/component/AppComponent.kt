package app.sargis.khlopuzyan.alias.di.component

import android.content.Context
import app.sargis.khlopuzyan.alias.di.module.AppModule
import app.sargis.khlopuzyan.alias.di.module.MainModule
import app.sargis.khlopuzyan.alias.ui.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by Sargis Khlopuzyan, on 2/14/2020.
 *
 * @author Sargis Khlopuzyan (sargis.khlopuzyan@fcc.am)
 */

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        MainModule::class
//        AlbumDetailsModule::class,
//        ArtistsSearchModule::class,
//        TopAlbumsModule::class
    ]
)

interface AppComponent : AndroidInjector<App> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Context): AppComponent
    }

}