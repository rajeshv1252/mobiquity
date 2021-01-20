package com.mobiquity.code.challenge.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mobiquity.code.challenge.data.local.db.dao.DummyDao
import com.mobiquity.code.challenge.data.local.db.dao.LocationDao
import com.mobiquity.code.challenge.data.local.db.entity.DummyEntity
import com.mobiquity.code.challenge.data.local.db.entity.LocationEntity
import javax.inject.Singleton

@Singleton
@Database(
    entities = [
        DummyEntity::class,
        LocationEntity::class
    ],
    exportSchema = false,
    version = 1
)
abstract class DatabaseService : RoomDatabase() {
    abstract fun dummyDao(): DummyDao
    abstract fun locationDao(): LocationDao
}