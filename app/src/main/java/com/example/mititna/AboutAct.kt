package com.example.mititna

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.mititna.databinding.ActivityAboutBinding

class AboutAct : AppCompatActivity() {

    private lateinit var binding: ActivityAboutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.navigationBarColor = this.resources.getColor(R.color.about)
        window.statusBarColor =  this.resources.getColor(R.color.shape_col_1)

        binding.backBtn.setOnClickListener {
            val intent = Intent(this@AboutAct, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
    override fun onBackPressed() {
        super.onBackPressed()
        val intentmain = Intent(this@AboutAct, MainActivity::class.java)
        startActivity(intentmain)
        finish()
    }
}