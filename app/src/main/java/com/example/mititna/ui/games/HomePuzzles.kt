package com.example.mititna.ui.games

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.mititna.MainActivity
import com.example.mititna.R
import com.example.mititna.databinding.ActivityHomePuzzlesBinding

class HomePuzzles : AppCompatActivity() {

    private var mMediaPlayer: MediaPlayer? = null
    private lateinit var binding: ActivityHomePuzzlesBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePuzzlesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        playSound()

        binding.btnStart.setOnClickListener {
            startActivity(Intent(this, MainMemory::class.java))
        }
        binding.btnInstructions.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.navigationBarColor = this.resources.getColor(R.color.containerhome)
    }
    private fun playSound(){
        if(mMediaPlayer == null){
            mMediaPlayer = MediaPlayer.create(this, R.raw.pistabcompressed)
            mMediaPlayer!!.isLooping = true
            mMediaPlayer!!.start()
        } else mMediaPlayer!!.start()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onStop() {
        super.onStop()
        if (mMediaPlayer != null) {
            mMediaPlayer!!.release()
            mMediaPlayer = null
        }
        finish()
    }
}