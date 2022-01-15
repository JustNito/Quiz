package ru.manzharovn.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.manzharovn.data.database.questions.Questions
import ru.manzharovn.data.database.questions.QuestionsDao

@Database(entities = [Questions::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun questionsDao(): QuestionsDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "app_database")
                    .createFromAsset("quiz.db")
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}