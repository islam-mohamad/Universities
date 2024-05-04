package com.islam.universities.di.module

import com.islam.core.di.scope.ActivityScoped
import com.islam.universities.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [UniversitiesModule::class])
    abstract fun bindMainActivity(): MainActivity
}