package eu.tutorial.moodle.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.Month

@Entity(tableName = "diaries")
data class Diary(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val currentDate : String,
    val emotions : Int,
    val diaryText : String,
)
