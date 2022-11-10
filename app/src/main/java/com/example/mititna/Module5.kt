package com.example.mititna

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.mititna.databinding.ActivityModule5Binding




class Module5 : AppCompatActivity() {
    private lateinit var binding: ActivityModule5Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModule5Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener{
            val intent = Intent(this@Module5, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.navigationBarColor = this.resources.getColor(R.color.colormodule)
        window.statusBarColor =  this.resources.getColor(R.color.colormodule)

    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this@Module5, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onStop() {
        super.onStop()
        val intent = Intent(this@Module5, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}