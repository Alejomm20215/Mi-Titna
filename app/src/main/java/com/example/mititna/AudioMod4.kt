package com.example.mititna

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mititna.databinding.ActivityAudioMod4Binding
import com.example.mititna.ui.modules.audiomod.AudioModules

class AudioMod4 : AppCompatActivity() {

    private var mMediaPlayer: MediaPlayer? = null
    private lateinit var binding : ActivityAudioMod4Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAudioMod4Binding.inflate(layoutInflater)
        setContentView(binding.root)
        clickListeners()
    }
    private fun clickListeners() {
        binding.backviewmod.setOnClickListener {
            startActivity(Intent(this, AudioModules::class.java))
            finish()
        }
        binding.palabrascot.setOnClickListener {
            //Stop previous sound
            pauseSound()
            stopSound()
            //Start sound
            if(mMediaPlayer == null){
                mMediaPlayer = MediaPlayer.create(this, R.raw.oracionesbasicas)
                mMediaPlayer!!.isLooping = false
                mMediaPlayer!!.start()
            } else mMediaPlayer!!.start()
        }
        binding.pronombresp.setOnClickListener {
            //Stop previous sound
            pauseSound()
            stopSound()
            //Start sound
            if(mMediaPlayer == null){
                mMediaPlayer = MediaPlayer.create(this, R.raw.pronombrespersonales)
                mMediaPlayer!!.isLooping = false
                mMediaPlayer!!.start()
            } else mMediaPlayer!!.start()
        }
        binding.module1.setOnClickListener {
            //Stop previous sound
            pauseSound()
            stopSound()
            //Start sound
            if(mMediaPlayer == null){
                mMediaPlayer = MediaPlayer.create(this, R.raw.palabrascotidianas)
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
                mMediaPlayer = MediaPlayer.create(this, R.raw.oracionescotidianas)
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
                mMediaPlayer = MediaPlayer.create(this, R.raw.oracionescotidianas2)
                mMediaPlayer!!.isLooping = false
                mMediaPlayer!!.start()
            } else mMediaPlayer!!.start()
        }
        binding.module3a.setOnClickListener {
            //Stop previous sound
            pauseSound()
            stopSound()
            //Start sound
            if(mMediaPlayer == null){
                mMediaPlayer = MediaPlayer.create(this, R.raw.oracionescotidianas3)
                mMediaPlayer!!.isLooping = false
                mMediaPlayer!!.start()
            } else mMediaPlayer!!.start()
        }
    }
    fun stopSound() {
        if (mMediaPlayer != null) {
            mMediaPlayer!!.stop()
            mMediaPlayer!!.release()
            mMediaPlayer = null
        }
    }
    fun pauseSound() {
        if (mMediaPlayer?.isPlaying == true) mMediaPlayer?.pause()
    }
    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, AudioModules::class.java))
        finish()
        stopSound()
    }
}