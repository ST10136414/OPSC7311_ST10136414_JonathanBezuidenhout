package com.example.opsc7311

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)

        val createProjBtn = findViewById<ImageView>(R.id.createProjectBtn)
        createProjBtn.setOnClickListener {
            val crProjIntent = Intent(this, CreateProject::class.java)
            startActivity(crProjIntent)
        }

        val inviteMemberBtn = findViewById<ImageView>(R.id.InviteMemberBtn)
        inviteMemberBtn.setOnClickListener {
            Toast.makeText(this,"This opens the invite member page", Toast.LENGTH_SHORT).show()
        }

        val viewReportBtn = findViewById<ImageView>(R.id.ViewReportBtn)
        viewReportBtn.setOnClickListener {
            Toast.makeText(this,"This opens the view report page", Toast.LENGTH_SHORT).show()
        }
    }
}