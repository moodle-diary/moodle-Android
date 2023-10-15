package eu.tutorial.moodle.data


class OfflineDiaryRepository(private val diaryDao: DiaryDao) : DiaryRepository {
    override suspend fun insertDiary(diary: Diary) = diaryDao.insertDiary(diary)
    override suspend fun insertActivity(activity: Activity) = diaryDao.insertActivity(activity)
    override suspend fun insertPlace(place: Place) = diaryDao.insertPlace(place)
    override suspend fun insertPeople(people: People) = diaryDao.insertPeople(people)
    override suspend fun insertFood(food: Food) = diaryDao.insertFood(food)
    override suspend fun insertImg(img: Img) = diaryDao.insertImg(img)

    override suspend fun updateDiary(diary: Diary) = diaryDao.update(diary)

    override fun getDiaries(currentDate: String): List<DiaryDto>  = diaryDao.getDiaries(currentDate)
    override fun getActivities(currentDate: String): List<ActivityDto> = diaryDao.getActivity(currentDate)
    override fun getPlaces(currentDate: String): List<PlaceDto> = diaryDao.getPlaces(currentDate)
    override fun getPeople(currentDate: String): List<PeopleDto> = diaryDao.getPeople(currentDate)
    override fun getFood(currentDate: String): List<FoodDto> = diaryDao.getFood(currentDate)
    override fun getImg(currentDate: String): List<ImgDto> = diaryDao.getImg(currentDate)


    override fun getActivityGrade(targetMonth: String): List<ActGrade> =
        diaryDao.getActivityGrade(targetMonth)

    override fun getPlaceGrade(targetMonth: String): List<PlaceGrade> =
        diaryDao.getPlaceGrade(targetMonth)

    override fun getPeopleGrade(targetMonth: String): List<PeopleGrade> =
        diaryDao.getPeopleGrade(targetMonth)

    override fun getFoodGrade(targetMonth: String): List<FoodGrade> =
        diaryDao.getFoodGrade(targetMonth)
}