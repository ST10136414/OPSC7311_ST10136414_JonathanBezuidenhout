package com.example.opsc7311

import Classes.UserClass
import android.content.Intent
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
        }
        else
        {
            userTxt.text = UserClass.loggedUser.userName.toString()
        }

        val sgnOutBtn = findViewById<Button>(R.id.btnSignOut)
        sgnOutBtn.setOnClickListener{
            var emptyUser = UserClass("","","")
            val sgnOutIntent = Intent(this, MainActivity::class.java)
            UserClass.loggedUser = emptyUser
            startActivity(sgnOutIntent)
        }
    }
}