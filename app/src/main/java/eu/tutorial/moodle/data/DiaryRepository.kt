package eu.tutorial.moodle.data

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class DiaryRepository(private val diaryDao: DiaryDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.

//    val allWords: Flow<List<Diary>> = diaryDao.getAlphabetizedWords()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
//    @Suppress("RedundantSuspendModifier")
//    @WorkerThread
    suspend fun insert(diary: Diary) {
        diaryDao.insert(diary)
    }
}