package eu.tutorial.moodle.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// TODO @Parcelize ??
@Entity(
    tableName = "diaries"
)
data class Diary(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val currentDate: String,

    val diaryText: String,
    val thought: String,
)

@Entity(
    tableName = "emotions"
)
data class Emotions(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "emotion_id")
    val id: Long = 0,

    val diaryId: Long = 0,
    val emotion: String,
)

@Entity(
    tableName = "cause",
)
data class Cause(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "cause_id")
    val id: Long = 0,

    val diaryId: Long = 0,
    val cause: String,
)

@Entity(
    tableName = "causeType"
)
data class CauseType(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val causeType: String
)

@Entity(
    tableName = "place",
)
data class Place(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "place_id")
    val id: Long = 0,

    val diaryId: Long = 0,
    val placeDescription: String,
)

@Entity(
    tableName = "placeType"
)
data class PlaceType(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val placeType: String
)

@Entity(
    tableName = "comment"
)
data class Comment(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val commentDate: String,
    val comment: String,
)


