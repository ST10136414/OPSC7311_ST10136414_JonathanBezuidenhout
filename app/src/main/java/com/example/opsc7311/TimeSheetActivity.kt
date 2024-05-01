package com.example.opsc7311

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.opsc7311.databinding.ActivityTimeSheetBinding

class TimeSheetActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_time_sheet)
        val button1 = findViewById<ImageButton>(R.id.CalendarButton)
        button1.setOnClickListener {
            val intent1 = Intent(this, CalendarActivity::class.java)
            startActivity(intent1)
        }
    }
}


