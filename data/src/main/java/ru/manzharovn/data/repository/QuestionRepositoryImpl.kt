package ru.manzharovn.data.repository

import ru.manzharovn.data.database.questions.Questions
import ru.manzharovn.data.database.questions.QuestionsDao
import ru.manzharovn.domain.models.Question
import ru.manzharovn.domain.repository.QuestionRepository

class QuestionRepositoryImpl(private val questionsDao: QuestionsDao) : QuestionRepository {

    override suspend fun getQuestion(id: Int): Question = mapToDomain(questionsDao.getQuestionById(id))

    override suspend fun numberOfRecords(): Int = questionsDao.numberOfRecords()

    private fun mapToDomain(questions: Questions): Question =
        Question(question = questions.question, answer = questions.answer, imgSrc = questions.imgSrc)
}