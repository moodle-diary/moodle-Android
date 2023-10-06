package eu.tutorial.moodle.data

import androidx.room.TypeConverter
import com.google.gson.Gson

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromEmoji(emoji: Emoji): String {
        return gson.toJson(emoji)
    }

    @TypeConverter
    fun toEntity(json: String): Emoji {
        return gson.fromJson(json, Emoji::class.java)
    }
}