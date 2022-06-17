package com.sample.newsdemo

import com.sample.newsdemo.repository.DataRepository
import com.sample.newsdemo.viewmodel.NewsViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val mainModule = module {

    single { DataRepository(get()) }

    single { createWebService() }

    viewModel { NewsViewModel(get()) }

}

fun createWebService(): NetWorkApi {
    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl("https://newsapi.org/v2/")
        .build()

    return retrofit.create(NetWorkApi::class.java)
}