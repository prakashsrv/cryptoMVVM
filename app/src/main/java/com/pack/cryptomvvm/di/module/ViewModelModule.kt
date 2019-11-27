package com.pack.cryptomvvm.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pack.cryptomvvm.viewmodels.HomeViewModels
import com.pack.cryptomvvm.viewmodels.ViewModelFactory
import com.pack.cryptomvvm.di.ViewModelKey
import com.pack.cryptomvvm.viewmodels.AccountViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
@Module
abstract class ViewModelModule {


    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModels::class)
    abstract fun bindViewModule(homeViewModel: HomeViewModels): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AccountViewModel::class)
    abstract fun bindAccountViewModel(accountViewModel: AccountViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}