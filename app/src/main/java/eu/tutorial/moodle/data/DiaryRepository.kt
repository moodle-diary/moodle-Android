package eu.tutorial.moodle.data

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

interface DiaryRepository{
    suspend fun insertDiary(diary: Diary) : Long
    suspend fun insertActivity(activity: Activity)
    suspend fun insertPlace(place: Place)
    suspend fun insertPeople(people: People)
    suspend fun insertFood(food: Food)
    suspend fun updateDiary(diary: Diary)
    fun getDiaries( currentDate : String ) : List<Diary>

}