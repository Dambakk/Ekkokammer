package net.dambakk.ekkokammer.android

import android.app.Application
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

val ekkoModules = module {
    viewModel { AppViewModel(androidApplication()) }
}


class EkkoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@EkkoApplication)
            modules(ekkoModules)
        }
    }
}
