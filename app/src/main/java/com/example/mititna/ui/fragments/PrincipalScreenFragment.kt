package com.example.mititna.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mititna.*
import com.example.mititna.databinding.FragmentPrincipalScreenBinding

class PrincipalScreenFragment : Fragment() {

    private lateinit var binding : FragmentPrincipalScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding  = FragmentPrincipalScreenBinding.inflate(layoutInflater)

        binding.chartContainer2.setOnClickListener {
            val intent = Intent(this@PrincipalScreenFragment.requireContext(), AboutAct::class.java)
            startActivity(intent)
        }

        binding.module1.setOnClickListener {
            val intent = Intent(this@PrincipalScreenFragment.requireContext(), Module_1::class.java)
            startActivity(intent)
        }
        binding.module2.setOnClickListener {
            val intent = Intent(this@PrincipalScreenFragment.requireContext(), Module2::class.java)
            startActivity(intent)
        }
        binding.module3.setOnClickListener {
            val intent = Intent(this@PrincipalScreenFragment.requireContext(), Module3::class.java)
            startActivity(intent)
        }
        binding.module4.setOnClickListener {
            val intent = Intent(this@PrincipalScreenFragment.requireContext(), Module4::class.java)
            startActivity(intent)
        }
        binding.module5.setOnClickListener {
            val intent = Intent(this@PrincipalScreenFragment.requireContext(), Module5::class.java)
            startActivity(intent)
        }

        return binding.root
    }
}
