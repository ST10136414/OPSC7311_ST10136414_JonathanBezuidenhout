package com.example.opsc7311

import Classes.UserClass
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SignUpActivity : AppCompatActivity() {

    //var userObj = UserClass()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //assigned a layout connection here
        setContentView(R.layout.activity_sign_up)


            val backtoLoginBtn = findViewById<Button>(R.id.previousPageBtnSignUp)
            backtoLoginBtn.setOnClickListener {
                val intent1 = Intent(this, MainActivity::class.java)
                startActivity(intent1)
            }
/*                val signUpBtnHere  = findViewById<Button>(R.id.signUpBtn)
                signUpBtnHere.setOnClickListener {
                    val intent2 = Intent(this, DashboardActivity::class.java)
                    startActivity(intent2)
            }*/


        //User saving function
            val userNameET = findViewById<EditText>(R.id.FullNameTxtBoxSignUp)
            val emailET = findViewById<EditText>(R.id.EmailTxtBoxSignUp)
            val passwordET = findViewById<EditText>(R.id.passwordTxtBoxSignUp)

            val signUpBtnHere = findViewById<Button>(R.id.signUpBtn)
            signUpBtnHere.setOnClickListener{
                //val signupIntent = Intent
                var userObj = UserClass()
                userObj.userName = userNameET.text.toString()
                userObj.userEmail= emailET.text.toString()
                userObj.passWord= passwordET.text.toString()

                // No issue?? `,:(
                userObj.storeTolist(userObj)
                Toast.makeText(this,"Username: "+userObj.userName+" Email: "+userObj.userEmail, Toast.LENGTH_SHORT).show()

            }
        /*

         */

    }
}