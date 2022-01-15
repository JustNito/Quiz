package ru.manzharovn.quiz.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import ru.manzharovn.data.database.AppDatabase
import ru.manzharovn.data.database.questions.QuestionsDao
import ru.manzharovn.data.repository.QuestionRepositoryImpl
import ru.manzharovn.domain.repository.QuestionRepository
import ru.manzharovn.quiz.MyApp
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    fun provideQuestionRepository(
        questionsDao: QuestionsDao
    ) : QuestionRepository {
        return QuestionRepositoryImpl(
            questionsDao = questionsDao
        )
    }

    @Singleton
    @Provides
    fun provideDb(context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Singleton
    @Provides
    fun provideLoginDao(db: AppDatabase): QuestionsDao {
        return db.questionsDao()
    }
}