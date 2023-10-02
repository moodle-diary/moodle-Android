package eu.tutorial.moodle.data

class OfflineDiaryRepository(private val diaryDao: DiaryDao) : DiaryRepository {
    override suspend fun insertDiary(diary: Diary) = diaryDao.insert(diary)
}