package eu.tutorial.moodle.data

import kotlinx.coroutines.flow.Flow

class OfflineDiaryRepository(private val diaryDao: DiaryDao) : DiaryRepository {
    override suspend fun insertDiary(diary: Diary) = diaryDao.insertDiary(diary)
    override suspend fun updateDiary(diary: Diary) = diaryDao.update(diary)

    override suspend fun insertActivity(activity: Activity) = diaryDao.insertActivity(activity)
    override suspend fun insertPlace(place: Place) = diaryDao.insertPlace(place)
    override suspend fun insertPeople(people: People) = diaryDao.insertPeople(people)

    override fun getDiaries(currentDate: String): List<Diary>  = diaryDao.getDiaries(currentDate)
}