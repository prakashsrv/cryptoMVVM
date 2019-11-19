package com.pack.cryptomvvm.di

import android.app.Application
import com.pack.cryptomvvm.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [SharedModule::class])
interface SharedComponent {


    fun inject(application: Application)
    fun inject(activity:MainActivity)


}