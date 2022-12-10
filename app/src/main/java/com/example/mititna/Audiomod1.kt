package com.example.mititna

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mititna.databinding.ActivityAudiomod1Binding
import com.example.mititna.ui.modules.audiomod.AudioModules

class Audiomod1 : AppCompatActivity() {

    private var mMediaPlayer : MediaPlayer? = null
    private lateinit var binding: ActivityAudiomod1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAudiomod1Binding.inflate(layoutInflater)
        setContentView(binding.root)
        clickListeners()
    }
    private fun clickListeners(){
        binding.backviewmod.setOnClickListener {
            startActivity(Intent(this@Audiomod1, AudioModules::class.java))
            finish()
        }
        //Saludos
        binding.Saludo1.setOnClickListener {
            //Stop previous sound
            pauseSound()
            stopSound()
            //Start sound
            if(mMediaPlayer == null){
                mMediaPlayer = MediaPlayer.create(this, R.raw.buenasnoches)
                mMediaPlayer!!.isLooping = false
                mMediaPlayer!!.start()
            } else mMediaPlayer!!.start()
        }
        binding.Saludo2.setOnClickListener {
            //Stop previous sound
            pauseSound()
            stopSound()
            //Start sound
            if(mMediaPlayer == null){
                mMediaPlayer = MediaPlayer.create(this, R.raw.buenastardelluviosa)
                mMediaPlayer!!.isLooping = false
                mMediaPlayer!!.start()
            } else mMediaPlayer!!.start()
        }
        binding.Saludo3.setOnClickListener {
            //Stop previous sound
            pauseSound()
            stopSound()
            //Start sound
            if(mMediaPlayer == null){
                mMediaPlayer = MediaPlayer.create(this, R.raw.buenastardes)
                mMediaPlayer!!.isLooping = false
                mMediaPlayer!!.start()
            } else mMediaPlayer!!.start()
        }
        binding.Saludo4.setOnClickListener {
            //Stop previous sound
            pauseSound()
            stopSound()
            //Start sound
            if(mMediaPlayer == null){
                mMediaPlayer = MediaPlayer.create(this, R.raw.buendia)
                mMediaPlayer!!.isLooping = false
                mMediaPlayer!!.start()
            } else mMediaPlayer!!.start()
        }
        binding.Saludo5.setOnClickListener {
            //Stop previous sound
            pauseSound()
            stopSound()
            //Start sound
            if(mMediaPlayer == null){
                mMediaPlayer = MediaPlayer.create(this, R.raw.diabonito)
                mMediaPlayer!!.isLooping = false
                mMediaPlayer!!.start()
            } else mMediaPlayer!!.start()
        }
        binding.Saludo6.setOnClickListener {
            //Stop previous sound
            pauseSound()
            stopSound()
            //Start sound
            if(mMediaPlayer == null){
                mMediaPlayer = MediaPlayer.create(this, R.raw.hastaluego)
                mMediaPlayer!!.isLooping = false
                mMediaPlayer!!.start()
            } else mMediaPlayer!!.start()
        }
        binding.Saludo7.setOnClickListener {
            //Stop previous sound
            pauseSound()
            stopSound()
            //Start sound
            if(mMediaPlayer == null){
                mMediaPlayer = MediaPlayer.create(this, R.raw.respuestaporlanoche)
                mMediaPlayer!!.isLooping = false
                mMediaPlayer!!.start()
            } else mMediaPlayer!!.start()
        }
        binding.Saludo8.setOnClickListener {
            //Stop previous sound
            pauseSound()
            stopSound()
            //Start sound
            if(mMediaPlayer == null){
                mMediaPlayer = MediaPlayer.create(this, R.raw.hastaelotrodia)
                mMediaPlayer!!.isLooping = false
                mMediaPlayer!!.start()
            } else mMediaPlayer!!.start()
        }

        //Numbers
        binding.module1.setOnClickListener {
            //Stop previous sound
            pauseSound()
            stopSound()
            //Start sound
            if(mMediaPlayer == null){
                mMediaPlayer = MediaPlayer.create(this, R.raw.uno)
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
                mMediaPlayer = MediaPlayer.create(this, R.raw.dos)
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
                mMediaPlayer = MediaPlayer.create(this, R.raw.tres)
                mMediaPlayer!!.isLooping = false
                mMediaPlayer!!.start()
            } else mMediaPlayer!!.start()
        }
        binding.module4.setOnClickListener {
            //Stop previous sound
            pauseSound()
            stopSound()
            //Start sound
            if(mMediaPlayer == null){
                mMediaPlayer = MediaPlayer.create(this, R.raw.cuatro)
                mMediaPlayer!!.isLooping = false
                mMediaPlayer!!.start()
            } else mMediaPlayer!!.start()
        }

        //Colors
        binding.module1b.setOnClickListener{
            //Stop previous sound
            pauseSound()
            stopSound()
            //Start sound
            if(mMediaPlayer == null){
                mMediaPlayer = MediaPlayer.create(this, R.raw.amarillo)
                mMediaPlayer!!.isLooping = false
                mMediaPlayer!!.start()
            } else mMediaPlayer!!.start()
        }
        binding.module2b.setOnClickListener{
            //Stop previous sound
            pauseSound()
            stopSound()
            //Start sound
            if(mMediaPlayer == null){
                mMediaPlayer = MediaPlayer.create(this, R.raw.azul)
                mMediaPlayer!!.isLooping = false
                mMediaPlayer!!.start()
            } else mMediaPlayer!!.start()
        }
        binding.module3b.setOnClickListener{
            //Stop previous sound
            pauseSound()
            stopSound()
            //Start sound
            if(mMediaPlayer == null){
                mMediaPlayer = MediaPlayer.create(this, R.raw.blanco)
                mMediaPlayer!!.isLooping = false
                mMediaPlayer!!.start()
            } else mMediaPlayer!!.start()
        }
        binding.module4b.setOnClickListener{
            //Stop previous sound
            pauseSound()
            stopSound()
            //Start sound
            if(mMediaPlayer == null){
                mMediaPlayer = MediaPlayer.create(this, R.raw.cafe)
                mMediaPlayer!!.isLooping = false
                mMediaPlayer!!.start()
            } else mMediaPlayer!!.start()
        }
        binding.module5b.setOnClickListener{
            //Stop previous sound
            pauseSound()
            stopSound()
            //Start sound
            if(mMediaPlayer == null){
                mMediaPlayer = MediaPlayer.create(this, R.raw.gris)
                mMediaPlayer!!.isLooping = false
                mMediaPlayer!!.start()
            } else mMediaPlayer!!.start()
        }
        binding.module1b1.setOnClickListener{
            //Stop previous sound
            pauseSound()
            stopSound()
            //Start sound
            if(mMediaPlayer == null){
                mMediaPlayer = MediaPlayer.create(this, R.raw.morado)
                mMediaPlayer!!.isLooping = false
                mMediaPlayer!!.start()
            } else mMediaPlayer!!.start()
        }
        binding.module2b1.setOnClickListener{
            //Stop previous sound
            pauseSound()
            stopSound()
            //Start sound
            if(mMediaPlayer == null){
                mMediaPlayer = MediaPlayer.create(this, R.raw.negro)
                mMediaPlayer!!.isLooping = false
                mMediaPlayer!!.start()
            } else mMediaPlayer!!.start()
        }
        binding.module3b1.setOnClickListener{
            //Stop previous sound
            pauseSound()
            stopSound()
            //Start sound
            if(mMediaPlayer == null){
                mMediaPlayer = MediaPlayer.create(this, R.raw.rojo)
                mMediaPlayer!!.isLooping = false
                mMediaPlayer!!.start()
            } else mMediaPlayer!!.start()
        }
        binding.module4b1.setOnClickListener{
            //Stop previous sound
            pauseSound()
            stopSound()
            //Start sound
            if(mMediaPlayer == null){
                mMediaPlayer = MediaPlayer.create(this, R.raw.verde)
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
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, AudioModules::class.java))
        finish()
        stopSound()
    }

}