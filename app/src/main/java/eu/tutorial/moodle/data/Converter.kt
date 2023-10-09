package eu.tutorial.moodle.data

import androidx.room.TypeConverter
import com.google.gson.Gson

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromActivityToString(activities: List<Activity>): String? {
        return gson.toJson(activities)
    }

    @TypeConverter
    fun toActivity(json: String): List<Activity> {
        return gson.fromJson(json, Array<Activity>::class.java).toList()
    }

    @TypeConverter
    fun fromPlaceToString(places: List<Place>): String? {
        return gson.toJson(places)
    }

    @TypeConverter
    fun toPlace(json: String): List<Place> {
        return gson.fromJson(json, Array<Place>::class.java).toList()
    }

    @TypeConverter
    fun fromPeopleToString(people: List<People>): String? {
        return gson.toJson(people)
    }

    @TypeConverter
    fun toPeople(json: String): List<People> {
        return gson.fromJson(json, Array<People>::class.java).toList()
    }
}