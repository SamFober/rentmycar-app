package nl.avans.rentmycar

import android.app.Application
import nl.avans.rentmycar.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class RentMyCarApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@RentMyCarApp)
            androidLogger()
            modules(appModule)
        }
    }
}