package com.example.mititna.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mititna.databinding.FragmentUserBinding
import com.example.mititna.volconfiguration

class UserFragment : Fragment() {

    private lateinit var binding: FragmentUserBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserBinding.inflate(layoutInflater)

        binding.confButtona.setOnClickListener {
            startActivity(Intent(this.requireContext(), volconfiguration::class.java))
        }

        return binding.root
    }
}