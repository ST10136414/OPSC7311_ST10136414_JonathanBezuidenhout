package com.example.opsc7311

import Classes.UserClass
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)

        val userTxt = findViewById<TextView>(R.id.txtUsername)

        if (UserClass.userMutableList.isNullOrEmpty())
        {
            userTxt.text = UserClass.loggedUser.userName.toString()
        }
        else
        {}
    }
}