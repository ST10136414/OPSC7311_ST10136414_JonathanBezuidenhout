package com.example.opsc7311

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.ImageButton
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val button1 = findViewById<Button>(R.id.btnSignInToYourAccount)
        button1?.setOnClickListener {
            val intent1 = Intent(this, SignInActivity::class.java)
            startActivity(intent1)
        }

        val button2 = findViewById<Button>(R.id.btnSignUpForFree)
        button2?.setOnClickListener {
            val intent2 = Intent(this, SignUpActivity::class.java)
            startActivity(intent2)
        }

        val button3 = findViewById<Button>(R.id.btnForgotPassword)
        button3?.setOnClickListener {
            val intent3 = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent3)
        }
    }
}