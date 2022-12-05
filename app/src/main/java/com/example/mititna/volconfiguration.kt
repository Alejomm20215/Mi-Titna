package com.example.mititna

import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.mititna.databinding.ActivityVolconfigurationBinding

class volconfiguration : AppCompatActivity() {

    private var mMediaPlayer: MediaPlayer? = null
    private lateinit var binding: ActivityVolconfigurationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVolconfigurationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        playSound()
        val am = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        var maxVol = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC)


        binding.volumeUp.setOnClickListener {
            val currVol = 75
            am.setStreamVolume(AudioManager.STREAM_MUSIC, currVol, 0)
        }
        binding.volumeDown.setOnClickListener {
            val currVol = 0
            am.setStreamVolume(AudioManager.STREAM_MUSIC, currVol, 0)
        }




        val editText = binding.editTextTextPersonName

        binding.btnBackconf.setOnClickListener {
            startActivity(Intent(this@volconfiguration, MainActivity::class.java))
        }
        binding.readybut.setOnClickListener {
            val nameText = binding.nameText
            val text = editText.text.toString()
            editText.isEnabled = false
            editText.visibility = View.GONE
            nameText.text = text
            nameText.visibility = View.VISIBLE
            binding.readybut.visibility = View.GONE
        }

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.navigationBarColor = this.resources.getColor(R.color.containerconfi)
    }

    private fun playSound() {
        if (mMediaPlayer == null) {
            mMediaPlayer = MediaPlayer.create(this, R.raw.pistadcompressed)
            mMediaPlayer!!.isLooping = true
            mMediaPlayer!!.start()
        } else mMediaPlayer!!.start()
    }
    fun pauseSound() {
        if (mMediaPlayer?.isPlaying == true) mMediaPlayer?.pause()
    }

    fun stopSound() {
        if (mMediaPlayer != null) {
            mMediaPlayer!!.stop()
            mMediaPlayer!!.release()
            mMediaPlayer = null
        }
    }

    override fun onStop() {
        super.onStop()
        if (mMediaPlayer != null) {
            mMediaPlayer!!.release()
            mMediaPlayer = null
        }
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}