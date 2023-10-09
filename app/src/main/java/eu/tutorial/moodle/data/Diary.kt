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

    val activities : List<Activity>,
    val places: List<Place>,
    val people: List<People>,

)

@Entity(
    tableName = "activity",
    foreignKeys = [
        ForeignKey(
            entity = Diary::class,
            parentColumns = ["diary_id"],
            childColumns = ["activity_id"],
            onDelete = CASCADE
        )
    ]
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
    foreignKeys = [
        ForeignKey(
            entity = Diary::class,
            parentColumns = ["diary_id"],
            childColumns = ["place_id"],
            onDelete = CASCADE
        )
    ]
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
    foreignKeys = [
        ForeignKey(
            entity = Diary::class,
            parentColumns = ["diary_id"],
            childColumns = ["people_id"],
            onDelete = CASCADE
        )
    ]
)
data class People(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "people_id")
    val id : Int = 0,

    val peopleId : Int,
    val peopleDescription : String,
)


