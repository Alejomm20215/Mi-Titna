package com.example.mititna.ui.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mititna.*
import com.example.mititna.databinding.FragmentPrincipalScreenBinding
import com.example.mititna.ui.modules.*
import com.example.mititna.ui.modules.audiomod.AudioModules
import kotlinx.android.synthetic.main.fragment_principal_screen.view.*

class PrincipalScreenFragment : Fragment() {

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var binding : FragmentPrincipalScreenBinding
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mediaPlayer = MediaPlayer.create(context, R.raw.marimbacompressed)
        binding  = FragmentPrincipalScreenBinding.inflate(layoutInflater)
        clickListeners()


        if(mediaPlayer.isPlaying) {
            binding.playMusic.setImageResource(R.drawable.ic_baseline_pause_circle_filled_24)
            mediaPlayer.start()
        } else {
            binding.playMusic.setImageResource(R.drawable.ic_baseline_play_circle_filled_24)
            mediaPlayer.pause()
        }

        return binding.root
    }


    private fun clickListeners(){
        //Activity change click listeners
        binding.chartContainer2.setOnClickListener {
            val intent = Intent(this@PrincipalScreenFragment.requireContext(), AboutAct::class.java)
            startActivity(intent)
        }
        //Open Modules listeners
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
        binding.moduleaud.setOnClickListener {
            startActivity(Intent(this@PrincipalScreenFragment.requireContext(), AudioModules::class.java))
        }
    }
}
