package com.islam.universities.di.module

import androidx.room.Room
import com.islam.core.di.scope.FragmentScoped
import com.islam.listing.data.repository.UniversitiesRepositoryImpl
import com.islam.listing.data.source.local.database.UniversityDatabase
import com.islam.listing.data.source.remote.UniversitiesApi
import com.islam.listing.domain.repository.UniversitiesRepository
import com.islam.listing.presentation.ListingNavigator
import com.islam.listing.presentation.view.UniversitiesFragment
import com.islam.universities.MainActivity
import com.islam.universities.navigator.Navigator
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import retrofit2.Retrofit

@Module
abstract class UniversitiesModule {
    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun homeFragment(): UniversitiesFragment

    @Binds
    abstract fun bindsUniversitiesRepository(impl: UniversitiesRepositoryImpl): UniversitiesRepository

    @Binds
    abstract fun bindsListingNavigator(impl: Navigator): ListingNavigator

    companion object {

        @Provides
        fun provideUniversitiesApi(retrofit: Retrofit) =
            retrofit.create(UniversitiesApi::class.java)

        @Provides
        fun provideUniversityDatabase(homeActivity: MainActivity) = Room.databaseBuilder(
            homeActivity, UniversityDatabase::class.java, "university_database"
        ).build()

        @Provides
        fun provideUniversityDao(database: UniversityDatabase) = database.universityDao()
    }
}