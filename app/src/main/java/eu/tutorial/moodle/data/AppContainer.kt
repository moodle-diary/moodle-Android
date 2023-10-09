package eu.tutorial.moodle.data

import android.content.Context

interface AppContainer {
    val diaryRepository: DiaryRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for [ItemsRepository]
     */
    override val diaryRepository: DiaryRepository by lazy {
        OfflineDiaryRepository(DiaryDatabase.getDatabase(context).diaryDao())
    }
}
