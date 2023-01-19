package com.example.mititna.ui.quiz

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mititna.R
import com.example.mititna.databinding.FragmentQuizBinding
import com.example.mititna.data.helper.getViewModelFactory
import com.example.mititna.ui.quiz.result.ResultViewModel

class QuizFragment : Fragment() {

    private lateinit var dataBinding: FragmentQuizBinding

    private val args: QuizFragmentArgs by navArgs()

    private val viewModel by viewModels<QuizViewModel> { getViewModelFactory(args.exerciseId) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = FragmentQuizBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }
        return dataBinding.root
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dataBinding.lifecycleOwner = this.viewLifecycleOwner
        setupClickListeners()
        setupLiveObservers()
        setupBackPressHandler()
    }

    private fun setupClickListeners() {
        dataBinding.btnCloseQuiz.setOnClickListener {
            showCloseQuizDialog()
        }
    }

    private fun showCloseQuizDialog() {
        val alertDialog: AlertDialog? = activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setTitle(R.string.close_quiz_dialog_title)
                setMessage(R.string.close_quiz_dialog_message)
                setPositiveButton(R.string.quit) { _, _ ->
                    findNavController().popBackStack()
                }
                setNegativeButton(android.R.string.cancel) { dialog, _ ->
                    dialog.cancel()
                }
            }
            builder.create()
        }
        alertDialog?.show()
    }

    private fun setupLiveObservers() {
        viewModel.questions.observe(viewLifecycleOwner, {
            if (it != null && it.isNotEmpty()) {
                viewModel.setupFirstQuestion()
            }
        })
        viewModel.quizState.observe(viewLifecycleOwner, { quizState ->
            when (quizState) {
                QuizViewModel.QuizState.NOT_ANSWERED -> dataBinding.mcqAnswerView.rgOptions.clearCheck()
                QuizViewModel.QuizState.COMPLETE -> openResultFragment(ResultViewModel.QuizResult.SUCCESS)
                QuizViewModel.QuizState.FAIL -> openResultFragment(ResultViewModel.QuizResult.FAILED)
                else -> {
                    // nada
                }
            }
        })
    }

    private fun setupBackPressHandler() {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            showCloseQuizDialog()
        }
    }

    private fun openResultFragment(quizResult: ResultViewModel.QuizResult) {
        val action =
            QuizFragmentDirections.actionQuizFragmentToResultFragment(
                /* lifeLeft = */ viewModel.lifeLeft.value ?: 0,
                /* quizResult = */ quizResult,
                /* exerciseId = */ args.exerciseId
            )
        findNavController().navigate(action)
    }
}