package ru.manzharovn.quiz.presentation.scorescreen

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import ru.manzharovn.domain.MAX_NUM_OF_QUESTIONS
import ru.manzharovn.quiz.MainActivity
import ru.manzharovn.quiz.R
import ru.manzharovn.quiz.databinding.FragmentScoreBinding
import ru.manzharovn.quiz.presentation.viewmodels.GameVIewModelFactory
import ru.manzharovn.quiz.presentation.viewmodels.GameViewModel
import javax.inject.Inject

class ScoreFragment: Fragment() {

    @Inject
    lateinit var gameVIewModelFactory: GameVIewModelFactory

    private val gameViewModel: GameViewModel by activityViewModels{
        gameVIewModelFactory
    }

    private var binding: FragmentScoreBinding? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentScoreBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = gameViewModel
            maxNumOfWords = MAX_NUM_OF_QUESTIONS
            restartButton.setOnClickListener{
                findNavController().navigate(R.id.action_scoreFragment_to_startFragment)
            }
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}