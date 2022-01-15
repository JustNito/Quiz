package ru.manzharovn.domain.usecases

import ru.manzharovn.domain.MAX_NUM_OF_QUESTIONS
import ru.manzharovn.domain.models.Question
import ru.manzharovn.domain.repository.QuestionRepository
import java.util.*
import javax.inject.Inject

class GetQuestionsUseCase (private val questionRepository: QuestionRepository) {

    suspend fun execute(): List<Question> {
        var listOfQuestions = mutableListOf<Question>()
        for(id in generateTenRandomId()){
            listOfQuestions.add(questionRepository.getQuestion(id))
        }
        return listOfQuestions
    }

    private  suspend fun generateTenRandomId(): List<Int> {
        val listOfId = mutableListOf<Int>()
        while(listOfId.size != MAX_NUM_OF_QUESTIONS) {
            val number = Random().nextInt(questionRepository.numberOfRecords()) + 1
            if(!listOfId.contains(number)){
                listOfId.add(number)
            }
        }
        return listOfId
    }
}