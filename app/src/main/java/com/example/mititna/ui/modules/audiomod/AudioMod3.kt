package com.example.mititna.ui.modules.audiomod

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.mititna.R
import com.example.mititna.databinding.ActivityAudioMod3Binding

class AudioMod3 : AppCompatActivity() {

    private lateinit var binding : ActivityAudioMod3Binding
    private var mMediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAudioMod3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        clickListeners()
        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.navigationBarColor = this.resources.getColor(R.color.bottom_nav)
    }

    private fun stopSound() {
        if (mMediaPlayer != null) {
            mMediaPlayer!!.stop()
            mMediaPlayer!!.release()
            mMediaPlayer = null
        }
    }
    private fun pauseSound() {
        if (mMediaPlayer?.isPlaying == true) mMediaPlayer?.pause()
    }

    private fun clickListeners(){
        binding.backviewmod.setOnClickListener {
            startActivity(Intent(this, AudioModules::class.java))
            finish()
        }
        //Medicinal Plants
        binding.Saludo1.setOnClickListener {
            //Stop previous sound
            pauseSound()
            stopSound()
            //Start sound
            if(mMediaPlayer == null){
                mMediaPlayer = MediaPlayer.create(this, R.raw.plantasmedicinales)
                mMediaPlayer!!.isLooping = false
                mMediaPlayer!!.start()
            } else mMediaPlayer!!.start()
        }
        //Animals
        binding.module1.setOnClickListener {
            //Stop previous sound
            pauseSound()
            stopSound()
            //Start sound
            if(mMediaPlayer == null){
                mMediaPlayer = MediaPlayer.create(this, R.raw.animales)
                mMediaPlayer!!.isLooping = false
                mMediaPlayer!!.start()
            } else mMediaPlayer!!.start()
        }
        binding.module2.setOnClickListener {
            //Stop previous sound
            pauseSound()
            stopSound()
            //Start sound
            if(mMediaPlayer == null){
                mMediaPlayer = MediaPlayer.create(this, R.raw.animalesdomesticos)
                mMediaPlayer!!.isLooping = false
                mMediaPlayer!!.start()
            } else mMediaPlayer!!.start()
        }
        binding.module3.setOnClickListener {
            //Stop previous sound
            pauseSound()
            stopSound()
            //Start sound
            if(mMediaPlayer == null){
                mMediaPlayer = MediaPlayer.create(this, R.raw.reptiles)
                mMediaPlayer!!.isLooping = false
                mMediaPlayer!!.start()
            } else mMediaPlayer!!.start()
        }
        binding.module1s.setOnClickListener {
            //Stop previous sound
            pauseSound()
            stopSound()
            //Start sound
            if(mMediaPlayer == null){
                mMediaPlayer = MediaPlayer.create(this, R.raw.reptiles2)
                mMediaPlayer!!.isLooping = false
                mMediaPlayer!!.start()
            } else mMediaPlayer!!.start()
        }
        //Lunar phases
        binding.module1b.setOnClickListener {
            //Stop previous sound
            pauseSound()
            stopSound()
            //Start sound
            if(mMediaPlayer == null){
                mMediaPlayer = MediaPlayer.create(this, R.raw.faseslunares)
                mMediaPlayer!!.isLooping = false
                mMediaPlayer!!.start()
            } else mMediaPlayer!!.start()
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, AudioModules::class.java))
        finish()
        stopSound()
    }
}