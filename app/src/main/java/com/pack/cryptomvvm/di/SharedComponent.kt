package com.pack.cryptomvvm.di

import android.app.Application
import com.pack.cryptomvvm.MainActivity
import com.pack.cryptomvvm.ui.fragment.Home
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [SharedModule::class])
interface SharedComponent {


    fun inject(application: Application)
    fun inject(activity:MainActivity)
    fun inject(fragment:Home)


}