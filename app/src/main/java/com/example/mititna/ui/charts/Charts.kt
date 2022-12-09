package com.example.mititna.ui.charts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mititna.R
import com.example.mititna.databinding.FragmentChartsBinding
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries

class Charts : Fragment() {


    lateinit var lineGraphView : GraphView
    private lateinit var binding: FragmentChartsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChartsBinding.inflate(layoutInflater)

        lineGraphView = binding.lineChart



        val series: LineGraphSeries<DataPoint> = LineGraphSeries(
            arrayOf(

            )
        )

        lineGraphView.animate()
        lineGraphView.viewport.isScrollable = true
        lineGraphView.viewport.isScalable = true
        lineGraphView.viewport.setScalableY(true)
        lineGraphView.viewport.setScrollableY(true)
        series.color = R.color.bottom_nav
        lineGraphView.addSeries(series)

        return binding.root
    }
}