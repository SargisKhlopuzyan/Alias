package app.sargis.khlopuzyan.alias.ui

import android.content.Context
import app.sargis.khlopuzyan.alias.BuildConfig
import app.sargis.khlopuzyan.alias.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber

/**
 * Created by Sargis Khlopuzyan, on 2/14/2020.
 *
 * @author Sargis Khlopuzyan (sargis.khlopuzyan@fcc.am)
 */
class App : DaggerApplication() {

    init {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {

        context = this

        return DaggerAppComponent
            .factory()
            .create(this)
    }

    companion object {

        private lateinit var context: Context

        fun getContext() = context
    }
}