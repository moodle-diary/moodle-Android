package eu.tutorial.moodle.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

@Dao
interface DiaryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE) // 인수가 겹치면 새 항목을 무시 한다.
    suspend fun insert(diary: Diary) // 별도의 스레드에서 실행하도록 한다. // 여기 suspend

    @Update
    suspend fun update(diary: Diary)

    @Delete
    suspend fun delete(diary: Diary)


}