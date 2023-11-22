package eu.tutorial.moodle.data


class OfflineDiaryRepository(private val diaryDao: DiaryDao) : DiaryRepository {
    override suspend fun insertDiary(diary: Diary) =
        diaryDao.insertDiary(diary)

    override suspend fun insertEmotion(emotions: Emotions) =
        diaryDao.insertEmotion(emotions)

    override suspend fun insertCause(cause: Cause) =
        diaryDao.insertCause(cause)

    override suspend fun insertPlace(place: Place) =
        diaryDao.insertPlace(place)

    override suspend fun insertComment(comment: Comment) =
        diaryDao.insertComment(comment)

    override suspend fun insertCauseType(causeType: CauseType) =
        diaryDao.insertCauseType(causeType)

    override suspend fun insertPlaceType(placeType: PlaceType) =
        diaryDao.insertPlaceType(placeType)

    override suspend fun updateDiary(diary: Diary) =
        diaryDao.update(diary)

    override fun getDiaries(currentDate: String): List<DiaryDto> =
        diaryDao.getDiaries(currentDate)

    override fun getEmotions(currentDate: String): List<IconDto> =
        diaryDao.getEmotions(currentDate)

    override fun getCauses(currentDate: String): List<IconDto> =
        diaryDao.getCauses(currentDate)

    override fun getPlaces(currentDate: String): List<IconDto> =
        diaryDao.getPlaces(currentDate)

    override fun getComments(commentDate: String): List<CommentDto> =
        diaryDao.getComments(commentDate)

    override fun getGreatDays(targetMonth: String): List<Int> =
        diaryDao.getGreatDays(targetMonth)

    override fun getRemindDays(targetMonth: String): List<Int> =
        diaryDao.getRemindDays(targetMonth)


    // targetMonth 넘겨줄 때에 뒤에 "%" 추가 해야 합니다. because of LIKE
    override fun getEmotionGrade(targetMonth: String): List<EmotionGrade> =
        diaryDao.getEmotionGrade(targetMonth)

    override fun getCauseGrade(targetMonth: String): List<CauseGrade> =
        diaryDao.getCauseGrade(targetMonth)

    override fun getPlaceGrade(targetMonth: String): List<PlaceGrade> =
        diaryDao.getPlaceGrade(targetMonth)

    override fun getCauseTypes(): List<CauseTypeDto> =
        diaryDao.getCauseTypes()

    override fun getPlaceTypes(): List<PlaceTypeDto> =
        diaryDao.getPlaceTypes()

    override fun getHourRate(targetMonth: String): List<TimeItem> =
        diaryDao.getHourRate(targetMonth)

    override fun deleteComment(commentId: Int) =
        diaryDao.deleteComment(commentId)
}