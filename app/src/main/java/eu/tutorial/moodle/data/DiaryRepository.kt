package eu.tutorial.moodle.data

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

interface DiaryRepository{
    suspend fun insertDiary(diary: Diary) : Long
    suspend fun insertCause(cause: Cause)
    suspend fun insertPlace(place: Place)
    suspend fun insertPeople(people: People)
    suspend fun insertFood(food: Food)
    suspend fun insertImg(img : Img)
    suspend fun insertComment(comment: Comment)
    suspend fun updateDiary(diary: Diary)
    fun getDiaries( currentDate : String ) : List<DiaryDto>
    fun getCauses( currentDate: String ) : List<CauseDto>
    fun getPlaces( currentDate: String ) : List<PlaceDto>
    fun getPeople( currentDate: String ) : List<PeopleDto>
    fun getFood( currentDate: String ) : List<FoodDto>
    fun getImg( currentDate: String ) : List<ImgDto>

    fun getCauseGrade(targetMonth: String) : List <CauseGrade>
    fun getPeopleGrade(targetMonth: String) : List <PeopleGrade>
    fun getPlaceGrade(targetMonth: String) : List <PlaceGrade>
    fun getFoodGrade(targetMonth: String) : List <FoodGrade>
    fun getComments(commentDate :String) : List<CommentDto>
}