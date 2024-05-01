package com.example.opsc7311

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.*

class TimerActivity : AppCompatActivity() {
    private lateinit var currentTimeTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_timer)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        currentTimeTextView = findViewById(R.id.currentTimeTextView)

        // Schedule a task to update the time every second
        currentTimeTextView.postDelayed(updateTimeRunnable, 1000)
    }

    private val updateTimeRunnable: Runnable = object : Runnable {
        override fun run() {
            updateTime()
            currentTimeTextView.postDelayed(this, 1000)
        }
    }

    private fun updateTime() {
        Log.d("TimerActivity", "updateTime() called")
        val sdf = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        val currentTime = sdf.format(Date())
        currentTimeTextView.text = "Current Time: " + currentTime
    }
}