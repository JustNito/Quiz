package ru.manzharovn.quiz

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.manzharovn.data.database.questions.Questions
import ru.manzharovn.data.database.questions.QuestionsDao
import ru.manzharovn.quiz.di.AppComponent
import ru.manzharovn.quiz.di.DaggerAppComponent

class MyApp : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}