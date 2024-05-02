package com.example.opsc7311

import Classes.UserClass
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SignInActivity : AppCompatActivity() {

    var userObj = UserClass()
    var userMutableList = userObj.userMutableList


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_in)


        val previousPageBtnAgain = findViewById<Button>(R.id.previousPageBtn)
        previousPageBtnAgain.setOnClickListener {
            val previousPageIntent = Intent(this, MainActivity::class.java)
            startActivity(previousPageIntent)
        }

        val forgotPassBtn = findViewById<Button>(R.id.forgotYourPasswordBtn)
        forgotPassBtn.setOnClickListener {
            val forgotPassIntent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(forgotPassIntent)
        }

        val emailET = findViewById<EditText>(R.id.emailTxtBoxSignIn)
        val passET = findViewById<EditText>(R.id.passwordTxtBoxSignIn)

        val signInBtnAgain = findViewById<Button>(R.id.signInBtn)
        signInBtnAgain.setOnClickListener {

            for (i in 0 until userMutableList.size )
            {
                /*
                    for (fruit in fruits) {
                    print("$fruit ")
                    }
                */

                if (emailET.text = this.userMutableList[i].userEmail.toString() )
                {
                    //toast(ThingFound), wait 2 seconds, then..
                    val signInIntent = Intent(this, DashboardActivity::class.java)
                    startActivity(signInIntent)
                }
                else
                {
                    //toast.retrymessage
                }
                //validate that user and pass exist
                //if (user.string = i.username.toString())
                //then
                //toast(ThingFound), wait 2 seconds, then..

            }
        }
    }
}