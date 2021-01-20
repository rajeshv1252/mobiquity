package com.mobiquity.code.challenge.data.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "locations")
data class LocationEntity(

    @PrimaryKey(autoGenerate = true)
    @NotNull
    val id: Long = 0,

    @ColumnInfo(name = "name")
    @NotNull
    val address: String,

    @ColumnInfo(name = "latitude")
    @NotNull
    val latitude: Double,

    @ColumnInfo(name = "longitude")
    @NotNull
    val longitude: Double

) {
    constructor() : this(0, "", 0.0,0.0)
}