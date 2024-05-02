package com.example.opsc7311

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

            val button1 = findViewById<Button>(R.id.previousPageBtnSignUp)
            button1.setOnClickListener {
                val intent1 = Intent(this, MainActivity::class.java)
                startActivity(intent1)
            }
                val button2 = findViewById<Button>(R.id.signInBtn)
                button1.setOnClickListener {
                    val intent2 = Intent(this, DashboardActivity::class.java)
                    startActivity(intent2)
            }
    }
}