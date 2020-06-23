package com.demo.demoapplication.koin

import com.demo.demoapplication.network.RetrofitClientInstance
import com.demo.demoapplication.ui.MainViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.applicationContext

val appModule = applicationContext {
    viewModel { MainViewModel(get()) }
    bean { RetrofitClientInstance().getRestClient()!! }
}
