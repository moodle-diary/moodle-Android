package eu.tutorial.moodle.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.Month

@Entity(tableName = "diaries")
data class Diary(
    @PrimaryKey
    val currentDate : LocalDate,
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