package ru.manzharovn.quiz.presentation.quizscreen

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.marginTop
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import ru.manzharovn.domain.MAX_NUM_OF_QUESTIONS
import ru.manzharovn.quiz.MainActivity
import ru.manzharovn.quiz.MyApp
import ru.manzharovn.quiz.R
import ru.manzharovn.quiz.databinding.FragmentQuizBinding
import ru.manzharovn.quiz.presentation.viewmodels.GameVIewModelFactory
import ru.manzharovn.quiz.presentation.viewmodels.GameViewModel
import java.io.InputStream
import javax.inject.Inject
import androidx.core.view.marginTop

class QuizFragment: Fragment(){

    private var binding: FragmentQuizBinding? = null

    @Inject
    lateinit var gameVIewModelFactory: GameVIewModelFactory

    private val gameViewModel: GameViewModel by activityViewModels{
        gameVIewModelFactory
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentQuizBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = gameViewModel
            maxNumOfQuestions = MAX_NUM_OF_QUESTIONS
            submit.setOnClickListener {
                onSubmitAnswer()
            }
            skip.setOnClickListener {
                onSkipQuestion()
            }
        }

        gameViewModel.imgsrc.observe(viewLifecycleOwner) { imagesrc ->
            loadImage(imagesrc)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun onSubmitAnswer(){
        var playerAnswer = binding?.answerInput?.text.toString()
        if(gameViewModel.isAnswerCorrect(playerAnswer)) {
            setErrorTextField(false)
            if (!gameViewModel.nextQuestion()) {
                findNavController().navigate(R.id.action_quizFragment2_to_scoreFragment)
            }
        } else {
            setErrorTextField(true)
        }
    }

    private fun onSkipQuestion() {
        if (gameViewModel.nextQuestion()) {
            setErrorTextField(false)
        } else {
            findNavController().navigate(R.id.action_quizFragment2_to_scoreFragment)
        }
    }

    private fun setErrorTextField(error: Boolean) {
        binding?.apply {
            if (error) {
                textField.isErrorEnabled = true
                textField.error = getString(R.string.try_again)
            } else {
                textField.isErrorEnabled = false
                answerInput.text = null
            }
        }
    }

    @SuppressLint("CheckResult")
    fun loadImage(imagesrc: String?){
        if(imagesrc != null){
            binding?.let {
                Glide
                    .with(requireContext())
                    .load(Uri.parse("file:///android_asset/quizImages/$imagesrc"))
                    .override(600)
                    .into(it.questionImage)
            }
        } else {
            binding?.questionImage?.setImageDrawable(null)
        }
    }
}
