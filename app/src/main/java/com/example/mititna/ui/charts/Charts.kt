package com.example.mititna.ui.charts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mititna.databinding.FragmentChartsBinding
import com.example.mititna.ui.quiz.result.ResultViewModel
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

class Charts() : Fragment() {

    private lateinit var binding: FragmentChartsBinding
    private lateinit var streakChart : LineChart

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChartsBinding.inflate(layoutInflater)
        streakChart = binding.lineChart

        streakChart.xAxis.valueFormatter = IndexAxisValueFormatter(ResultViewModel.DAYS_OF_WEEK)
        streakChart.xAxis.axisMinimum = 0f
        streakChart.xAxis.axisMaximum = 6f
        streakChart.axisLeft.axisMinimum = 0f
        streakChart.axisRight.axisMinimum = 0f
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val todayStreakDone = arguments?.getBoolean("todayStreakDone")

        if(todayStreakDone == true){
            val data = LineData()
            data.addDataSet(LineDataSet(getEntries(), "label"))
            streakChart.data = data
        }
    }
    private fun getEntries(): ArrayList<Entry>{
        val entries = ArrayList<Entry>()
        for (i in 0 until ResultViewModel.DAYS_OF_WEEK.size) {
            val xp = 10
            entries.add(Entry(i.toFloat(), xp.toFloat()))
        }
        return entries
    }
}
