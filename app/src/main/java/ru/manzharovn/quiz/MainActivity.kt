package ru.manzharovn.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import ru.manzharovn.data.database.questions.QuestionsDao
import ru.manzharovn.domain.usecases.GetQuestionsUseCase
import ru.manzharovn.quiz.di.AppComponent

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var navContrloller: NavController
    val appComponent: AppComponent by lazy {
        (application as MyApp).appComponent
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navContrloller = navHostFragment.navController
    }

    override fun onBackPressed() {
    }

}
