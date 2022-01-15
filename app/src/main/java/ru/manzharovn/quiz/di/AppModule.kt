package ru.manzharovn.quiz.di

import dagger.Module
import dagger.Provides
import ru.manzharovn.domain.usecases.GetQuestionsUseCase
import ru.manzharovn.quiz.presentation.viewmodels.GameVIewModelFactory

@Module
class AppModule {

    @Provides
    fun provideGameViewModelFactory(
        getQuestionsUseCase: GetQuestionsUseCase
    ) : GameVIewModelFactory {
        return GameVIewModelFactory(
            getQuestionsUseCase = getQuestionsUseCase
        )
    }


}