package com.example.mititna.ui.games

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mititna.R
import com.example.mititna.databinding.ActivityMainMemoryBinding
import kotlinx.android.synthetic.main.activity_main_memory.*

class MainMemory : AppCompatActivity() {

    private lateinit var buttons : List<ImageButton>
    private lateinit var cards : List<Carta>
    private var indexOfSingleSelectedCard: Int? = null
    private var mMediaPlayer : MediaPlayer? = null
    private lateinit var binding: ActivityMainMemoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMemoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        playSound()


        buttons = listOf(imageButton, imageButton2, imageButton3, imageButton4, imageButton5,
            imageButton6, imageButton7, imageButton8)

        val images = mutableListOf(
            R.drawable.puchavec,
            R.drawable.kut_avec,
            R.drawable.kabuashivec,
            R.drawable.urakutimavec
        )
        images.addAll(images)
        images.shuffle()

        cards = buttons.indices.map { index ->
            Carta(images[index])
        }
        buttons.forEachIndexed { index, button ->
            button.setOnClickListener {
                updateModels(index)
                updateViews()
            }
        }
        binding.buttonbackgc.setOnClickListener {
            startActivity(Intent(this, HomePuzzles::class.java))
            finish()
        }
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.navigationBarColor = this.resources.getColor(R.color.memorycontainer)
    }

    private fun updateViews() {
        cards.forEachIndexed { index, card ->
            val button = buttons[index]
            if (card.isMatched) {
                button.alpha = 0.1f
            }
            button.setImageResource(if (card.isFaceUp) card.identifier else R.drawable.ic_home)
        }

    }

    private fun updateModels(position: Int) {
        val card = cards[position]
        if (card.isFaceUp) {
            Toast.makeText(this, "Movimiento inválido!", Toast.LENGTH_SHORT).show()
            return
        }
        if (cards.all { it.isMatched }) {
            Toast.makeText(this, "Bien hecho!", Toast.LENGTH_SHORT).show()
            val images = mutableListOf(
                R.drawable.puchavec,
                R.drawable.kut_avec,
                R.drawable.kabuashivec,
                R.drawable.urakutimavec
            )
            images.addAll(images)
            images.shuffle()
            cards = buttons.indices.map { index ->
                Carta(images[index])
            }
            updateViews()
        }
        indexOfSingleSelectedCard = if (indexOfSingleSelectedCard == null) {
            restoreCards()
            position
        } else {
            checkForMatch(indexOfSingleSelectedCard!!, position)
            null
        }
        card.isFaceUp = !card.isFaceUp
    }

    private fun restoreCards() {
        for (card in cards) {
            if (!card.isMatched) {
                card.isFaceUp = false
            }
        }
    }

    private fun checkForMatch(position1: Int, position2: Int) {
        if (cards[position1].identifier == cards[position2].identifier) {
            Toast.makeText(this, "Completado!", Toast.LENGTH_SHORT).show()
            cards[position1].isMatched = true
            cards[position2].isMatched = true
        }
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
        startActivity(Intent(this, HomePuzzles::class.java))
        finish()
    }

    override fun onStop() {
        super.onStop()
        if (mMediaPlayer != null) {
            mMediaPlayer!!.release()
            mMediaPlayer = null
        }
    }
}