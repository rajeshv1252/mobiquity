package com.mobiquity.code.challenge.data.local.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.mobiquity.code.challenge.data.local.db.entity.LocationEntity
import io.reactivex.Single


@Dao
interface LocationDao {

    @Query("SELECT * FROM locations")
    fun getAllLocations(): Single<List<LocationEntity>>

    @Insert
    fun insert(locationEntity: LocationEntity): Single<Long>

    @Delete
    fun delete(locationEntity: LocationEntity): Single<Int>
}