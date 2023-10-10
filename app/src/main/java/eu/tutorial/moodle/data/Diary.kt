package eu.tutorial.moodle.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

// TODO @Parcelize ??
@TypeConverters(Converters::class)
@Entity(tableName = "diaries")
data class Diary(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "diary_id")
    val id : Int = 0,

    val currentDate : String,
    val emotions : Int,
    val diaryText : String,
)

@Entity(
    tableName = "activity",
)
data class Activity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "activity_id")
    val id : Int = 0,

    val activityId : Int,
    val activityDescription : String,
)

@Entity(
    tableName = "place",
)
data class Place(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "place_id")
    val id : Int = 0,

    val placeId : Int,
    val placeDescription : String,
)

@Entity(
    tableName = "people",
)
data class People(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "people_id")
    val id : Int = 0,

    val peopleId : Int,
    val peopleDescription : String,
)


