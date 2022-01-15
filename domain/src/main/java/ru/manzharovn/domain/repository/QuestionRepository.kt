package ru.manzharovn.domain.repository

import ru.manzharovn.domain.models.Question

interface QuestionRepository {

    suspend fun getQuestion(id: Int): Question

    suspend fun numberOfRecords(): Int
}