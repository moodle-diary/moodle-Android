package eu.tutorial.moodle.data

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

interface DiaryRepository{
    suspend fun insertDiary(diary: Diary)
    suspend fun updateDiary(diary: Diary)
    fun getDiaries( currentDate : String ) : List<Diary>
}