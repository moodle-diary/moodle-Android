package eu.tutorial.moodle.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

// TODO @Parcelize ??
@Entity(
    tableName = "diaries"
)
data class Diary(
    @PrimaryKey(autoGenerate = true)
    val id : Long = 0,

    val currentDate : String,
    val emotions : Int,
    val diaryText : String,
)

@Entity(
    tableName = "cause",
)
data class Cause(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "cause_id")
    val id : Long = 0,

    val diaryId : Long = 0,
    val cause : String,
)

@Entity(
    tableName = "place",
)
data class Place(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "place_id")
    val id : Long = 0,

    val diaryId : Long = 0,
    val placeDescription : String,
)
// This is why
@Entity(
    tableName = "comment"
)
data class Comment(
    @PrimaryKey(autoGenerate = true)
    val id : Long = 0,

    val commentDate : String,
    val comment : String,
)

