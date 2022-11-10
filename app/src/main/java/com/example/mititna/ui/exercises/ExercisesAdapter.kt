package com.example.mititna.ui.exercises

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mititna.R
import com.example.mititna.data.model.db.Exercise
import com.example.mititna.databinding.ExerciseItemBinding


class ExercisesAdapter(
    private val onClick: (String) -> Unit
) : RecyclerView.Adapter<ExercisesAdapter.ExerciseViewHolder>() {

    private var exercises: List<Exercise>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder =
        ExerciseViewHolder(
            ExerciseItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = exercises?.size ?: 0

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        exercises?.get(position)?.let { exercise ->
            holder.bind(exercise)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun refreshData(newData: List<Exercise>?) {
        newData?.apply {
            exercises = this
            notifyDataSetChanged()
        }
    }

    inner class ExerciseViewHolder(private val dataBinding: ExerciseItemBinding) :
        RecyclerView.ViewHolder(dataBinding.root), View.OnClickListener {
        init {
            dataBinding.root.setOnClickListener(this)
        }

        fun bind(exercise: Exercise) {
            dataBinding.exercise = exercise
            dataBinding.executePendingBindings()
        }

        override fun onClick(p0: View?) {
            exercises?.apply {
                if (this[adapterPosition].isUnlocked) {
                    onClick(this[adapterPosition].id)
                } else {
                    Toast.makeText(p0!!.context, R.string.lesson_is_locked_msg, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}