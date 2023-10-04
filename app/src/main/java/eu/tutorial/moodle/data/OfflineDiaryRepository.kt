package eu.tutorial.moodle.data

import kotlinx.coroutines.flow.Flow

class OfflineDiaryRepository(private val diaryDao: DiaryDao) : DiaryRepository {
    override suspend fun insertDiary(diary: Diary) = diaryDao.insert(diary)
    override suspend fun updateDiary(diary: Diary) = diaryDao.update(diary)
    override fun getDiaries(currentDate: String): List<Diary>  = diaryDao.getDiaries(currentDate)
}