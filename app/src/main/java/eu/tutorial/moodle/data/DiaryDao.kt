package eu.tutorial.moodle.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface DiaryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE) // 인수가 겹치면 새 항목을 무시 한다.
    suspend fun insertDiary(diary: Diary) : Long // 별도의 스레드에서 실행하도록 한다. // 여기 suspend
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertActivity(activity: Activity)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPlace(place: Place)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPeople(people: People)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFood(food: Food)

    @Update
    suspend fun update(diary: Diary)
    @Delete
    suspend fun delete(diary: Diary)

    // 오늘 하루의 emotion, text
    @Query(
        "SELECT emotions, diaryText FROM diaries WHERE diaries.currentDate = :currentDate"
    )
    fun getDiaries(currentDate : String) : List<DiaryDto>

    // 오늘 하루의 activity
    @Query(
        "SELECT activity.activityDescription " +
        "FROM diaries " +
        "INNER JOIN activity on activity.diaryId = diaries.id "+
        "WHERE diaries.currentDate = :currentDate"
    )
    fun getActivity(currentDate : String) : List<ActivityDto>

    // 오늘 하루의 place
    @Query(
        "SELECT place.placeDescription " +
                "FROM diaries " +
                "INNER JOIN place on place.diaryId = diaries.id "+
                "WHERE diaries.currentDate = :currentDate"
    )
    fun getPlaces(currentDate : String) : List<PlaceDto>

    // 오늘 하루의 people
    @Query(
        "SELECT people.peopleDescription " +
                "FROM diaries " +
                "INNER JOIN people on people.diaryId = diaries.id "+
                "WHERE diaries.currentDate = :currentDate"
    )
    fun getPeople(currentDate : String) : List<PeopleDto>


}