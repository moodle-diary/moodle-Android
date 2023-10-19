package eu.tutorial.moodle.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
// 이때의 version??
@Database(
    entities = [Diary::class, Cause::class, Place::class,
        People::class, Food::class, Img::class, Comment::class],
    version = 3, exportSchema = false
) // 백업 스키마 유지 하지 않도록
abstract class DiaryDatabase : RoomDatabase() {
    abstract fun diaryDao(): DiaryDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var Instance: DiaryDatabase? = null

        fun getDatabase(context : Context): DiaryDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, DiaryDatabase::class.java, "diary_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }

}