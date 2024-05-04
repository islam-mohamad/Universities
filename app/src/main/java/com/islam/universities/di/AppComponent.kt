package com.islam.universities.di

import android.app.Application
import com.islam.universities.UniversitiesApp
import com.islam.universities.di.module.ActivityBindingModule
import com.islam.universities.di.module.AppModule
import com.islam.universities.di.module.DispatcherModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class, DispatcherModule::class, ActivityBindingModule::class
        , AndroidSupportInjectionModule::class]
)
interface AppComponent : AndroidInjector<UniversitiesApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}