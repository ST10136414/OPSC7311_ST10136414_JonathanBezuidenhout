package com.example.opsc7311

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.widget.Button

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Set padding using window insets
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets*/
        val button1 = findViewById<Button>(R.id.SignInToYourAccountBtn)
        button1.setOnClickListener{}
        val intent1 = Intent(this, SignInActivity::class.java)
        startActivity(intent)
        val button2 = findViewById<Button>(R.id.SignUpForFreeBtn )
        button1.setOnClickListener {}
        val intent2 = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
        val button3 = findViewById<Button>(R.id.ForgotPasswordBtn)
        button1.setOnClickListener {}
        val intent3 = Intent(this, ForgotPasswordActivity::class.java)
        startActivity(intent)
    }
}

