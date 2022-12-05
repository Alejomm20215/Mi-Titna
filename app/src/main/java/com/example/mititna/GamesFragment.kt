package com.example.mititna

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mititna.databinding.FragmentGamesBinding


class GamesFragment : Fragment() {

    private lateinit var binding: FragmentGamesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        binding = FragmentGamesBinding.inflate(layoutInflater)

        binding.startgame.setOnClickListener {
            startActivity(Intent(this@GamesFragment.requireContext(), HomePuzzles::class.java))
        }
        return binding.root
    }

}