package ru.manzharovn.quiz.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.manzharovn.quiz.presentation.quizscreen.QuizFragment
import ru.manzharovn.quiz.presentation.scorescreen.ScoreFragment
import ru.manzharovn.quiz.presentation.startscreen.StartFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataModule::class, DomainModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(fragment: QuizFragment)
    fun inject(fragment: ScoreFragment)
    fun inject(fragment: StartFragment)
}