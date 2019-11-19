package com.pack.cryptomvvm.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.pack.cryptomvvm.BaseApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SharedModule(private val application: BaseApplication) {

    @Provides
    fun context():Context{

        return application
    }

    @Provides
    @Singleton
    fun sharedPref(context: Context):SharedPreferences{

        return context.getSharedPreferences("API",MODE_PRIVATE);
    }
}