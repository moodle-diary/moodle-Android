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
    suspend fun insertDiary(diary: Diary): Long // 별도의 스레드에서 실행하도록 한다. // 여기 suspend

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertEmotion(emotions: Emotions)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCause(cause: Cause)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPlace(place: Place)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertComment(comment: Comment)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCauseType(causeType: CauseType)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPlaceType(placeType: PlaceType)

    @Update
    suspend fun update(diary: Diary)

    @Delete
    suspend fun delete(diary: Diary)

    // 오늘 하루의 emotion, text
    @Query(
        "SELECT hour, diaryText FROM diaries WHERE diaries.currentDate = :currentDate"
    )
    fun getDiaries(currentDate: String): List<DiaryDto>

    @Query(
        "SELECT emotions.emotion as iconDescription " +
                "FROM diaries " +
                "INNER JOIN emotions on emotions.diaryId = diaries.id " +
                "WHERE diaries.currentDate = :currentDate"
    )
    fun getEmotions(currentDate: String): List<IconDto>

    // 우울의 cause
    @Query(
        "SELECT cause.cause as iconDescription " +
                "FROM diaries " +
                "INNER JOIN cause on cause.diaryId = diaries.id " +
                "WHERE diaries.currentDate = :currentDate"
    )
    fun getCauses(currentDate: String): List<IconDto>

    // 오늘 하루의 place
    @Query(
        "SELECT place.placeDescription as iconDescription " +
                "FROM diaries " +
                "INNER JOIN place on place.diaryId = diaries.id " +
                "WHERE diaries.currentDate = :currentDate"
    )
    fun getPlaces(currentDate: String): List<IconDto>

    @Query(
        "SELECT emotion, COUNT(emotion_id) AS cnt FROM diaries " +
                "INNER JOIN emotions on emotions.diaryId = diaries.id " +
                "WHERE diaries.currentDate LIKE :targetMonth " +
                "GROUP BY emotion " +
                "ORDER BY COUNT(emotion_id) DESC"
    )
    fun getEmotionGrade(targetMonth: String): List<EmotionGrade>

    @Query(
        "SELECT cause, COUNT(cause_id) AS cnt FROM diaries " +
                "INNER JOIN cause on cause.diaryId = diaries.id " +
                "WHERE diaries.currentDate LIKE :targetMonth " +
                "GROUP BY cause " +
                "ORDER BY COUNT(cause_id) DESC"
    )
    fun getCauseGrade(targetMonth: String): List<CauseGrade>

    @Query(
        "SELECT placeDescription, COUNT(place_id) AS cnt FROM diaries " +
                "INNER JOIN place on place.diaryId = diaries.id " +
                "WHERE diaries.currentDate LIKE :targetMonth " +
                "GROUP BY placeDescription " +
                "ORDER BY COUNT(place_id) DESC"
    )
    fun getPlaceGrade(targetMonth: String): List<PlaceGrade>

    @Query(
        "SELECT id, commentDate, comment FROM comment WHERE commentDate = :commentDate"
    )
    fun getComments(commentDate: String): List<CommentDto>

    @Query(
        "SELECT causeType, iconId FROM causeType"
    )
    fun getCauseTypes(): List<CauseTypeDto>

    @Query(
        "SELECT placeType, iconId FROM placeType"
    )
    fun getPlaceTypes(): List<PlaceTypeDto>

    @Query(
        "DELETE from comment where id = :commentId"
    )
    fun deleteComment(commentId: Int)

    @Query(
        "SELECT COUNT(currentDate) as cnt FROM diaries  " +
                "INNER JOIN emotions on emotions.diaryId = diaries.id " +
                "where currentDate like :targetMonth " +
                "Group by currentDate " +
                "having COUNT(currentDate) = 1 and emotion == \"행복\"\n" +
                ""
    )
    fun getGreatDays(targetMonth: String): Int

    @Query(
        "with recursive tmp as (" +
                "select 0 as n " +
                "union all " +
                "select n + 1 " +
                "from tmp " +
                "where n <= 24" +
                ") " +
                "select tmp.n as hour, count(id) as cnt " +
                "from tmp " +
                "left join diaries on tmp.n = hour " +
                "group by tmp.n " +
                "order by tmp.n"
    )
    fun getHourRate(): List<TimeItem>
}