package com.example.mititna.ui.lessons


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mititna.data.helper.getViewModelFactory
import com.example.mititna.databinding.FragmentLessonsBinding


class LessonsFragment : Fragment() {

    private val viewModel by viewModels<LessonsViewModel>{ getViewModelFactory(args.exerciseId) }

    private lateinit var dataBinding: FragmentLessonsBinding

    private val args: LessonsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = FragmentLessonsBinding.inflate(inflater, container, false).apply {
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
    }

    private fun setupClickListeners() {
        dataBinding.btnQuiz.setOnClickListener {
            openQuizFragment()
        }
    }

    private fun openQuizFragment() {
        val action =
            LessonsFragmentDirections.actionLessonsFragmentToQuizFragment(
                args.exerciseId
            )
        findNavController().navigate(action)
    }

    private fun setupLiveObservers() {
        viewModel.lessons.observe(this.viewLifecycleOwner) { lessons ->
            if (lessons.isNotEmpty()) {
                viewModel.setupLesson()
            }
        }
    }


}