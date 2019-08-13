package pe.demo.papayin

import android.app.Application
import org.koin.core.context.startKoin
import pe.demo.papayin.di.presentationModule
import pe.demo.papayin.di.repositoryModule
import pe.demo.papayin.remote.di.networkModule

class PDApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(presentationModule, networkModule, repositoryModule)
        }
    }
}