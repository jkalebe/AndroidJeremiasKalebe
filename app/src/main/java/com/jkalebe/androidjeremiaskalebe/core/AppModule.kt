package com.jkalebe.androidjeremiaskalebe.core

import com.jkalebe.androidjeremiaskalebe.data.remote.ApiInterface
import com.jkalebe.androidjeremiaskalebe.core.api.RetrofitClient
import com.jkalebe.androidjeremiaskalebe.data.repository.ApiRepository
import com.jkalebe.androidjeremiaskalebe.views.client.ClientViewModel
import org.koin.dsl.module

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