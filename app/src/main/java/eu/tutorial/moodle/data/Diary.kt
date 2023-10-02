package eu.tutorial.moodle.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.Month

@Entity(tableName = "diaries")
data class Diary(
    @PrimaryKey(autoGenerate = false)
    val currentDate : String,
    val diaryText : String,
)


@Entity(
    tableName = "emotions",
    primaryKeys = ["month", "date", "emotion"]
)
data class Emotion(
    val month: Month,
    val date: LocalDate,
    val emotion : String,
    val cnt : Int
)