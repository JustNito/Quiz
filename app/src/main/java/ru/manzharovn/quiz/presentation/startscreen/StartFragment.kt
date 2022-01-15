package ru.manzharovn.quiz.presentation.startscreen

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
import ru.manzharovn.quiz.MainActivity
import ru.manzharovn.quiz.R
import ru.manzharovn.quiz.databinding.FragmentStartBinding
import ru.manzharovn.quiz.presentation.viewmodels.GameVIewModelFactory
import ru.manzharovn.quiz.presentation.viewmodels.GameViewModel
import javax.inject.Inject


class StartFragment : Fragment() {

    @Inject
    lateinit var gameVIewModelFactory: GameVIewModelFactory

    private val gameViewModel: GameViewModel by activityViewModels{
        gameVIewModelFactory
    }

    private var binding: FragmentStartBinding? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentStartBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gameViewModel.resetData()
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            startButton.setOnClickListener {
                startGame()
            }
        }
    }

    private fun startGame(){
        gameViewModel.nextQuestion()
        findNavController().navigate(R.id.action_startFragment_to_quizFragment2)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}