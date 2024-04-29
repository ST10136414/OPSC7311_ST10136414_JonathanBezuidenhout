package com.example.opsc7311

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class SignInActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_in)
        val button1 = findViewById<Button>(R.id.previousPageBtn)
        button1.setOnClickListener{}
        val intent1 = Intent(this, MainActivity::class.java)
        startActivity(intent)
        val button2 = findViewById<Button>(R.id.previousPageBtn)
        button1.setOnClickListener{}
        val intent2 = Intent(this, ForgotPasswordActivity::class.java)
        startActivity(intent)
    }
}
