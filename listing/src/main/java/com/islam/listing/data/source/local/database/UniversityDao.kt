package com.islam.listing.data.source.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.islam.listing.data.model.UniversityModel

@Dao
interface UniversityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUniversities(universities: List<UniversityModel>)

    @Query("SELECT * FROM universities")
    suspend fun getUniversities(): List<UniversityModel>
}