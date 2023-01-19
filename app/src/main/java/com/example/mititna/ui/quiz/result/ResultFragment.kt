package com.example.mititna.ui.quiz.result


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mititna.R
import com.example.mititna.data.helper.getResultViewModelFactory
import com.example.mititna.databinding.FragmentResultBinding


class ResultFragment : Fragment() {

    private lateinit var dataBinding: FragmentResultBinding

    private val args: ResultFragmentArgs by navArgs()


    private val viewModel by viewModels<ResultViewModel> {
        getResultViewModelFactory(
            exerciseId = args.exerciseId,
            quizResult = args.quizResult,
            lifeLeft = args.lifeLeft
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = FragmentResultBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }

        return dataBinding.root
    }


    private fun removeFragment(){
        val fragment = parentFragmentManager.findFragmentById(R.id.resultFragment)
        fragment?.let{
            val transaction = parentFragmentManager.beginTransaction()
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            transaction.remove(it).commitAllowingStateLoss()
        }
    }


    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupClickListeners()
        setupBackPressHandler()
    }

    private fun setupClickListeners() {
        dataBinding.btnTryAgain.setOnClickListener {
            val action =
                ResultFragmentDirections.actionResultFragmentToQuizFragment(
                    args.exerciseId
                )
            findNavController().navigate(action)
        }
        dataBinding.btnContinue.setOnClickListener {
            goToExercisesScreen()
            removeFragment()
        }


    }
    private fun setupBackPressHandler() {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            goToExercisesScreen()
        }
    }
    private fun goToExercisesScreen() {
        val action =
            ResultFragmentDirections.actionResultFragmentToExercisesFragment()
        findNavController().navigate(action)
    }

}

