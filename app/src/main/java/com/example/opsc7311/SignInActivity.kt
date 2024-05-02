package com.example.opsc7311

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_in)
        val button1 = findViewById<Button>(R.id.previousPageBtn)
        button1.setOnClickListener {
            val intent1 = Intent(this, MainActivity::class.java)
            startActivity(intent1)
        }
        val button2 = findViewById<Button>(R.id.forgotYourPasswordBtn)
        button2.setOnClickListener {
            val intent2 = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent2)

        }
    }
}