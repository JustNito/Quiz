package ru.manzharovn.quiz.di

import dagger.Module
import dagger.Provides
import ru.manzharovn.domain.repository.QuestionRepository
import ru.manzharovn.domain.usecases.GetQuestionsUseCase

@Module
class DomainModule {

    @Provides
    fun provideGetQuestionUseCase(
        questionRepository: QuestionRepository
    ) : GetQuestionsUseCase {
        return GetQuestionsUseCase(
            questionRepository = questionRepository
        )
    }

}