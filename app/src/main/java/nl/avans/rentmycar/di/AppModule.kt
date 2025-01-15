package nl.avans.rentmycar.di

import android.content.Context
import androidx.datastore.dataStore
import io.ktor.client.engine.cio.CIO
import nl.avans.rentmycar.auth.data.networking.RemoteAuthDataSource
import nl.avans.rentmycar.auth.domain.AuthDataSource
import nl.avans.rentmycar.auth.presentation.login.LoginViewModel
import nl.avans.rentmycar.core.data.networking.HttpClientFactory
import nl.avans.rentmycar.core.domain.UserPreferencesSerializer
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val Context.userPreferenceDatastore by dataStore(
    fileName = "",
    serializer = UserPreferencesSerializer
)

val appModule = module {
    single { HttpClientFactory.create(CIO.create()) }
    viewModelOf(::LoginViewModel)
    singleOf(::RemoteAuthDataSource).bind<AuthDataSource>()
    single { provideDataStore(get()) }
}

fun provideDataStore(context: Context) = context.userPreferenceDatastore