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

    val emojis : List<Emoji>,

)

@Entity(
    tableName = "emoji",
    foreignKeys = [
        ForeignKey(
            entity = Diary::class,
            parentColumns = ["diary_id"],
            childColumns = ["emoji_id"],
            onDelete = CASCADE
        )
    ]
)
data class Emoji(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "emoji_id")
    val id : Int = 0,

    val emojiId : Int,
    val emojiCategory : String,
    val emojiDescription : String,
)


