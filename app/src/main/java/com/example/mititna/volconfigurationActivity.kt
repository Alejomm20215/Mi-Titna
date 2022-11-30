package com.example.mititna

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mititna.databinding.ActivityConfigurationBinding

class configurationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConfigurationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigurationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backBtnconf.setOnClickListener {
            startActivity(Intent(this@configurationActivity, MainActivity::class.java))
            finish()
        }
    }
}