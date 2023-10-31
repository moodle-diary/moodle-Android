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
    suspend fun insertCause(cause: Cause)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPlace(place: Place)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertComment(comment : Comment)

    @Update
    suspend fun update(diary: Diary)
    @Delete
    suspend fun delete(diary: Diary)

    // 오늘 하루의 emotion, text
    @Query(
        "SELECT emotions, diaryText FROM diaries WHERE diaries.currentDate = :currentDate"
    )
    fun getDiaries(currentDate : String) : List<DiaryDto>

    // 우울의 cause
    @Query(
        "SELECT cause.cause " +
        "FROM diaries " +
        "INNER JOIN cause on cause.diaryId = diaries.id "+
        "WHERE diaries.currentDate = :currentDate"
    )
    fun getCauses(currentDate : String) : List<CauseDto>

    // 오늘 하루의 place
    @Query(
        "SELECT place.placeDescription " +
                "FROM diaries " +
                "INNER JOIN place on place.diaryId = diaries.id "+
                "WHERE diaries.currentDate = :currentDate"
    )
    fun getPlaces(currentDate : String) : List<PlaceDto>

    @Query(
        "SELECT cause, COUNT(cause_id) AS cnt FROM diaries " +
                "INNER JOIN cause on cause.diaryId = diaries.id " +
                "WHERE diaries.currentDate LIKE :targetMonth " +
                "GROUP BY cause " +
                "ORDER BY COUNT(cause_id) DESC"
    )fun getCauseGrade(targetMonth: String) : List<CauseGrade>

    @Query(
        "SELECT placeDescription, COUNT(place_id) AS cnt FROM diaries " +
                "INNER JOIN place on place.diaryId = diaries.id " +
                "WHERE diaries.currentDate LIKE :targetMonth " +
                "GROUP BY placeDescription " +
                "ORDER BY COUNT(place_id) DESC"
    )fun getPlaceGrade(targetMonth: String) : List<PlaceGrade>

    @Query(
        "SELECT id, commentDate, comment FROM comment WHERE commentDate = :commentDate"
    )fun getComments(commentDate : String) :List<CommentDto>

    @Query(
        "DELETE from comment where id = :commentId"
    )
    fun deleteComment(commentId: Int)
}