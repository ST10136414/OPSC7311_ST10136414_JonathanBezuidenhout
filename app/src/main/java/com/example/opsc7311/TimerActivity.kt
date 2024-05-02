package com.example.opsc7311

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.*
import android.os.Handler
import android.os.Looper
import android.widget.Toast

class TimerActivity : AppCompatActivity() {
    private lateinit var currentTimeTextView: TextView
    private lateinit var timerText: TextView
    private lateinit var rootView: ConstraintLayout
    private lateinit var playButton: ImageView
    private var isPlaying: Boolean = false
    private var elapsedTime: Long = 0L
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_timer)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.territoryHeadingTxt)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        currentTimeTextView = findViewById(R.id.currentTimeTextView)
        timerText = findViewById(R.id.Timer)
        rootView = findViewById(R.id.territoryHeadingTxt)
        playButton = findViewById(R.id.PlayTimer)

        rootView.setBackgroundColor(ContextCompat.getColor(this, android.R.color.black))

        // Set click listener for play button
        playButton.setOnClickListener {
            togglePlay()
        }

        // Schedule a task to update the time every second
        currentTimeTextView.postDelayed(updateTimeRunnable, 1000)


        //navigation
        //Navigates to dashboard
        val navDashBtn = findViewById<ImageView>(R.id.navBtnDash)
        navDashBtn.setOnClickListener{
            val dashIntent = Intent(this, DashboardActivity::class.java)
            startActivity(dashIntent)
        }
        //navigates to timer page
        val navTmrBtn = findViewById<ImageView>(R.id.navBtnTimer)
        navTmrBtn.setOnClickListener{
            val tmrIntent = Intent(this, TimerActivity::class.java)
            startActivity(tmrIntent)
        }
        //Navigates to timesheet
        val navTimShtBtn = findViewById<ImageView>(R.id.navBtnTimeSheet)
        navTimShtBtn.setOnClickListener{
            val timeSheetIntent = Intent(this, TimeSheetActivity::class.java)
            startActivity(timeSheetIntent)
        }
        //navigates to More Options page
        val navMreBtn = findViewById<ImageView>(R.id.navBtnMore)
        navMreBtn.setOnClickListener {
            val navMreIntent = Intent(this, MoreOptionsActivity::class.java)
            startActivity(navMreIntent)
        }
        //navigates to report page
        val navReportBtn = findViewById<ImageView>(R.id.navBtnReport)
        navReportBtn.setOnClickListener {
            Toast.makeText(this,"This opens the view report page", Toast.LENGTH_SHORT).show()
        }

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
    private fun togglePlay() {
        if (isPlaying) {
            // Stop playing
            isPlaying = false
            rootView.setBackgroundColor(ContextCompat.getColor(this, android.R.color.black))

            timerText.setTextColor(ContextCompat.getColor(this, R.color.white))
            currentTimeTextView.setTextColor(ContextCompat.getColor(this, R.color.white))
            handler.removeCallbacks(updateTimerRunnable)
        } else {
            // Start playing
            isPlaying = true
            rootView.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_orange_light))
            timerText.setTextColor(ContextCompat.getColor(this, R.color.black))
            currentTimeTextView.setTextColor(ContextCompat.getColor(this, R.color.black))
            handler.postDelayed(updateTimerRunnable, 10)
        }
    }
    private val updateTimerRunnable: Runnable = object : Runnable {
        override fun run() {
            elapsedTime += 10
            val seconds = elapsedTime / 1000
            val minutes = seconds / 60
            val hours = minutes / 60
            val timeString = String.format("%02d:%02d:%02d.%03d", hours, minutes % 60, seconds % 60, elapsedTime % 1000)
            timerText.text = timeString
            handler.postDelayed(this, 10)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(updateTimerRunnable)
    }
}