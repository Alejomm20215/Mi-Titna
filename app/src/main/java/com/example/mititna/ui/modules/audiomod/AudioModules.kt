package com.example.mititna.ui.modules.audiomod

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mititna.*
import com.example.mititna.databinding.ActivityAudioModulesBinding

class AudioModules : AppCompatActivity() {

    private lateinit var binding : ActivityAudioModulesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAudioModulesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Activity changing listeners
        binding.backhomebtn.setOnClickListener {
            startActivity(Intent(this@AudioModules, MainActivity::class.java))
            finish()
        }
        binding.mod1cont.setOnClickListener {
            startActivity(Intent(this@AudioModules, Audiomod1::class.java))
            finish()
        }
        binding.mod2cont.setOnClickListener{
            startActivity(Intent(this@AudioModules, AudioMod2::class.java))
            finish()
        }
        binding.mod3cont.setOnClickListener {
            startActivity(Intent(this@AudioModules, AudioMod3::class.java))
            finish()
        }
        binding.mod4cont.setOnClickListener {
            startActivity(Intent(this@AudioModules, AudioMod4::class.java))
            finish()
        }
        binding.mod5cont.setOnClickListener {
            startActivity(Intent(this@AudioModules, AudioMod5::class.java))
            finish()
        }
    }

    override fun onStop() {
        super.onStop()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}