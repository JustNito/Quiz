package ru.manzharovn.quiz.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.manzharovn.domain.usecases.GetQuestionsUseCase

class GameVIewModelFactory(
    private val getQuestionsUseCase: GetQuestionsUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GameViewModel(
            getQuestionsUseCase = getQuestionsUseCase
        ) as T
    }

}