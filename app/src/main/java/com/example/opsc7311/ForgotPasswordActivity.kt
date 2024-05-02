package com.example.opsc7311

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.widget.Button
import androidx.activity.enableEdgeToEdge

class ForgotPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_forgot_password)
            val button1 = findViewById<Button>(R.id.backToSignInBtn)
            button1.setOnClickListener {
                val intent1 = Intent(this, SignInActivity::class.java)
                startActivity(intent1)

        }
    }
}