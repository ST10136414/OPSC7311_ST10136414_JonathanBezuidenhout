package com.example.opsc7311

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)
        val signOutButton = findViewById<Button>(R.id.btnSignOut)
        signOutButton.setOnClickListener {
            val SignOutIntent = Intent(this, MainActivity::class.java)
            startActivity(SignOutIntent)
        }
    }
}