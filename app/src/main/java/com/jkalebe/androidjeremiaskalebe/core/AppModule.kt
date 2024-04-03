package com.jkalebe.androidjeremiaskalebe.core

import androidx.room.Room
import com.jkalebe.androidjeremiaskalebe.core.database.AppDatabase
import com.jkalebe.androidjeremiaskalebe.data.remote.ApiInterface
import com.jkalebe.androidjeremiaskalebe.core.api.RetrofitClient
import com.jkalebe.androidjeremiaskalebe.data.repository.ApiRepository
import com.jkalebe.androidjeremiaskalebe.views.client.ClientViewModel
import org.koin.dsl.module

private const val DATABASE_NAME = "maxima.db"
val repositoryModule = module {
    single { ApiRepository(get ()) }
}

val viewModule = module {
    single { ClientViewModel(get()) }
}

val networkModule = module {
    single { RetrofitClient().provideHttpClient() }
    single { RetrofitClient().provideConverterFactory() }
    single { RetrofitClient().provideRetrofit(get(),get()) }
    single { RetrofitClient().provideService(get(), ApiInterface::class.java) }
}


val databaseModule = module {
    single { Room.databaseBuilder(get(), AppDatabase::class.java, DATABASE_NAME).build() }
    single { get<AppDatabase>().orderDAO() }
    single { get<AppDatabase>().clientDAO() }
    single { get<AppDatabase>().contactDAO() }
}