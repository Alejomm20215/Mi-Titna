package com.example.mititna.ui.exercises

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mititna.R
import com.example.mititna.databinding.FragmentExercises2Binding
import com.example.mititna.data.helper.getViewModelFactory

class ExercisesFragment2 : Fragment() {

    private val viewModel by viewModels<ExercisesViewModel> { getViewModelFactory() }

    private lateinit var dataBinding: FragmentExercises2Binding
    private lateinit var exercisesAdapter: ExercisesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = FragmentExercises2Binding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }
        setHasOptionsMenu(true)
        return dataBinding.root
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dataBinding.lifecycleOwner = this.viewLifecycleOwner
        setupRecyclerView()
        setupListAdapter()
        setupLiveObservers()
    }

    @Deprecated(
        "Deprecated in Java", ReplaceWith(
            "inflater.inflate(R.menu.main_menu, menu)",
            "com.example.mititna.R"
        )
    )
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                openSettingsFragment()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun setupLiveObservers() {
        viewModel.exercises.observe(viewLifecycleOwner, {
            exercisesAdapter.refreshData(it)
        })
    }

    private fun setupRecyclerView() {
        // layout manager is setup in xml
        dataBinding.exercisesList.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager.VERTICAL
            )
        )
    }

    private fun setupListAdapter() {
        exercisesAdapter = ExercisesAdapter { exerciseId ->
            openLessonsFragment(exerciseId)
        }
        dataBinding.exercisesList.adapter = exercisesAdapter
    }

    private fun openLessonsFragment(exerciseId: String) {
        val action =
            ExercisesFragmentDirections.actionExercisesFragmentToLessonsFragment(
                exerciseId
            )
        findNavController().navigate(action)
    }

    private fun openSettingsFragment() {
        val action =
            ExercisesFragmentDirections.actionExercisesFragmentToSettingsFragment()
        findNavController().navigate(action)
    }
}