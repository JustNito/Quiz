package ru.manzharovn.data.database.questions

import androidx.room.Dao
import androidx.room.Query

@Dao
interface QuestionsDao {
    @Query("SELECT * FROM questions WHERE id = :id")
    suspend fun getQuestionById(id: Int) : Questions

    @Query("SELECT COUNT(*) FROM questions")
    suspend fun numberOfRecords(): Int
}