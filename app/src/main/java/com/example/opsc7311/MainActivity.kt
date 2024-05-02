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
import android.widget.ImageView
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //Sign up activity navigation
        val signUpHereBtn = findViewById<MaterialButton>(R.id.btnSignUpForFree)
        signUpHereBtn.setOnClickListener {
            val signUpIntent = Intent(this, SignUpActivity::class.java)
            startActivity(signUpIntent)
        }

        //Doing now
        //Sign in activity navigation
        val signInBtnHere = findViewById<Button>(R.id.btnSignInToYourAccount)
        signInBtnHere.setOnClickListener {
            val signInIntent = Intent(this, SignInActivity::class.java)
            startActivity(signInIntent)
        }

        // to sort out next
        val forgotPassBtn = findViewById<Button>(R.id.btnForgotPassword)
        forgotPassBtn.setOnClickListener {
            val forgotPassIntent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(forgotPassIntent)
        }
    }
}