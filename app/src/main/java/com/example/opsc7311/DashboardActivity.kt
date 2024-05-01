package com.example.opsc7311

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)
        val button1 = findViewById<ImageButton>(R.id.createProjectBtn)
        button1.setOnClickListener {
            val intent1 = Intent(this, CreateProject::class.java)
            startActivity(intent1)
        }
    }
}