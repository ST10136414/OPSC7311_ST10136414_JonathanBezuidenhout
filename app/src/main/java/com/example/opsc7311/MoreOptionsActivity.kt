package com.example.opsc7311

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MoreOptionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_more_options)

        val button1 = findViewById<ImageButton>(R.id.profileBtn)
        button1.setOnClickListener {
            val intent1 = Intent(this, ProfileActivity::class.java)
            startActivity(intent1)
        }
        val button2 = findViewById<ImageButton>(R.id.manageProjectsBtn)
        button2.setOnClickListener {
            val intent2 = Intent(this, Manage_projects::class.java)
            startActivity(intent2)
        }
        val button3 = findViewById<ImageButton>(R.id.territoryBtn)
        button3.setOnClickListener {
            val intent3 = Intent(this, TerritoryActivity::class.java)
            startActivity(intent3)
        }
        val button4 = findViewById<Button>(R.id.backToDashBtn)
        button4.setOnClickListener {
            val intent4 = Intent(this, DashboardActivity::class.java)
            startActivity(intent4)
        }
    }
}