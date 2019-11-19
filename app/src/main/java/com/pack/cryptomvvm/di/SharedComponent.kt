package com.pack.cryptomvvm.di

import android.app.Application
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [SharedModule::class])
interface SharedComponent {


    fun inject(application: Application)


}