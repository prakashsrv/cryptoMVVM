package com.pack.cryptomvvm

import android.app.Application
import com.pack.cryptomvvm.di.DaggerSharedComponent
import com.pack.cryptomvvm.di.SharedComponent
import com.pack.cryptomvvm.di.module.SharedModule

class BaseApplication:Application() {

    lateinit var component:SharedComponent


    override fun onCreate() {
        super.onCreate()

        component=DaggerSharedComponent.builder().sharedModule(
            SharedModule(
                this
            )
        ).build()
    }


    fun getSharedComponent():SharedComponent{

        return component
    }

}