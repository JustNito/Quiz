package ru.manzharovn.quiz.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.manzharovn.domain.SCORE_PER_QUESTION
import ru.manzharovn.domain.models.Question
import ru.manzharovn.domain.usecases.GetQuestionsUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GameViewModel (private val getQuestionsUseCase: GetQuestionsUseCase) : ViewModel() {

    private lateinit var listOfQuestions: List<Question>

    private lateinit var answer: String

    private val _numOfCorrectAnswers = MutableLiveData(0)
    val numOfCorrectAnswers: LiveData<Int>
        get() = _numOfCorrectAnswers

    private val _score = MutableLiveData(0)
    val score: LiveData<Int>
        get() = _score

    private val _currentQuestionCount = MutableLiveData(0)
    val currentQuestionCount: LiveData<Int>
        get() = _currentQuestionCount

    private val _question = MutableLiveData("")
    val question: LiveData<String>
        get() = _question

    private val _imgsrc = MutableLiveData("")
    val imgsrc: LiveData<String>
        get() = _imgsrc

    private fun getNextQuestion() {
        val index = _currentQuestionCount.value!!
        listOfQuestions[index].let {
            answer = it.answer
            _question.value = it.question
            _imgsrc.value = it.imgSrc
        }
        _currentQuestionCount.value = _currentQuestionCount.value?.inc()
    }

    fun isAnswerCorrect(playerAnswer: String): Boolean {
        return if(playerAnswer.equals(answer, true)){
            _numOfCorrectAnswers.value = _numOfCorrectAnswers.value?.inc()
            increaseScore()
            true
        } else {
            false
        }
    }
    private fun increaseScore(){
        _score.value = _score.value?.plus(SCORE_PER_QUESTION)
    }

    fun nextQuestion(): Boolean {
        return if(_currentQuestionCount.value!! < 10) {
            getNextQuestion()
            true
        } else {
            false
        }
    }

    fun resetData(){
        _score.value = 0
        _currentQuestionCount.value = 0
        viewModelScope.launch {
            listOfQuestions = getQuestionsUseCase.execute()
        }
        _numOfCorrectAnswers.value = 0
    }
}