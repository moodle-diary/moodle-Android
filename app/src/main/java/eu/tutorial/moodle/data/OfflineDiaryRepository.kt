package eu.tutorial.moodle.data


class OfflineDiaryRepository(private val diaryDao: DiaryDao) : DiaryRepository {
    override suspend fun insertDiary(diary: Diary) = diaryDao.insertDiary(diary)
    override suspend fun insertCause(cause: Cause) = diaryDao.insertCause(cause)
    override suspend fun insertPlace(place: Place) = diaryDao.insertPlace(place)
    override suspend fun insertComment(comment: Comment) = diaryDao.insertComment(comment)
    override suspend fun updateDiary(diary: Diary) = diaryDao.update(diary)

    override fun getDiaries(currentDate: String): List<DiaryDto> =
        diaryDao.getDiaries(currentDate)
    override fun getCauses(currentDate: String): List<CauseDto> =
        diaryDao.getCauses(currentDate)
    override fun getPlaces(currentDate: String): List<PlaceDto> =
        diaryDao.getPlaces(currentDate)
    override fun getComments(commentDate: String): List<CommentDto> =
        diaryDao.getComments(commentDate)
// TODO : targetMonth 넘겨줄 때에 뒤에 "%" 추가 해야 합니다. because of LIKE
    override fun getCauseGrade(targetMonth: String): List<CauseGrade> =
        diaryDao.getCauseGrade(targetMonth)

    override fun getPlaceGrade(targetMonth: String): List<PlaceGrade> =
        diaryDao.getPlaceGrade(targetMonth)
}