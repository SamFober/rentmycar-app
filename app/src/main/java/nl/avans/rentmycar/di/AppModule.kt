package nl.avans.rentmycar.di

import android.content.Context
import androidx.datastore.dataStore
import io.ktor.client.engine.cio.CIO
import nl.avans.rentmycar.auth.data.IAuthDataSource
import nl.avans.rentmycar.auth.data.UserPreferencesSerializer
import nl.avans.rentmycar.auth.data.networking.RemoteAuthDataSource
import nl.avans.rentmycar.auth.domain.ITokenManager
import nl.avans.rentmycar.auth.domain.TokenManager
import nl.avans.rentmycar.auth.presentation.login.LoginViewModel
import nl.avans.rentmycar.auth.presentation.register.RegisterViewModel
import nl.avans.rentmycar.core.data.networking.HttpClientFactory
import nl.avans.rentmycar.rental.data.IRentalDataSource
import nl.avans.rentmycar.rental.data.networking.RemoteRentalDataSource
import nl.avans.rentmycar.rental.presentation.booking.BookRentalViewModel
import nl.avans.rentmycar.rental.presentation.offer.RentalOfferListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val Context.userTokensDataStore by dataStore(
    fileName = "user-tokens",
    serializer = UserPreferencesSerializer
)

val appModule = module {
    single { HttpClientFactory.create(CIO.create(), get()) }

    viewModelOf(::LoginViewModel)
    viewModelOf(::RegisterViewModel)
    viewModelOf(::BookRentalViewModel)
    viewModelOf(::RentalOfferListViewModel)

    singleOf(::TokenManager).bind<ITokenManager>()

    singleOf(::RemoteAuthDataSource).bind<IAuthDataSource>()
    singleOf(::RemoteRentalDataSource).bind<IRentalDataSource>()

    single { provideUserTokensDataStore(get()) }
}

fun provideUserTokensDataStore(context: Context) = context.userTokensDataStore