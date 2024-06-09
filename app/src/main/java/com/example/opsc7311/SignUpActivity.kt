/*package com.example.opsc7311

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
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase
    var userObj = UserClass()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //assigned a layout connection here
        setContentView(R.layout.activity_sign_up)

        // Initialize Firebase
        database = FirebaseDatabase.getInstance()

         fun uploadTextToFirebase(text: String) {
            val myRef = database.getReference("users")
            myRef.setValue(text)
        }


            val backtoLoginBtn = findViewById<Button>(R.id.previousPageBtnSignUp)
            backtoLoginBtn.setOnClickListener {
                val intent1 = Intent(this, MainActivity::class.java)
                startActivity(intent1)
            }
         fun sanitizeEmail(email: String): String {
            return email.replace(".", ",")
        }

        //User saving function
            val userNameET = findViewById<EditText>(R.id.FullNameTxtBoxSignUp)
            val emailET = findViewById<EditText>(R.id.EmailTxtBoxSignUp)
            val passwordET = findViewById<EditText>(R.id.passwordTxtBoxSignUp)

            val signUpBtnHere = findViewById<Button>(R.id.signUpBtn)
            signUpBtnHere.setOnClickListener{
                //val signupIntent = Intent
                //var userObj = UserClass()
                //old values
                userObj.userName = userNameET.text.toString().trim()
                userObj.userEmail= emailET.text.toString().trim()
                userObj.passWord= passwordET.text.toString().trim()
                //new values
                val userName = userNameET.text.toString().trim()
                val email = emailET.text.toString().trim()
                val password = passwordET.text.toString().trim()
                uploadTextToFirebase(userName)
                uploadTextToFirebase(email)
                uploadTextToFirebase(password)

                /*if (userName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                    val sanitizedEmail = sanitizeEmail(email)
                    val user = UserClass(userName, password, email)
                    // Store user data in Firebase Realtime Database
                    database.child(sanitizedEmail).setValue(user).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "User registered successfully", Toast.LENGTH_SHORT).show()
                            UserClass.userMutableList.add(user)
                        } else {
                            Toast.makeText(this, "Registration failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }*/
                }
                // No issue?? `,:(
                UserClass.userMutableList.add(userObj)
                Toast.makeText(this,"Username: "+UserClass.userMutableList[0].userName+" Email: "+UserClass.userMutableList[0].userEmail, Toast.LENGTH_SHORT).show()

            }

    }*/
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
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // Initialize Firebase
        database = FirebaseDatabase.getInstance()

        val backtoLoginBtn = findViewById<Button>(R.id.previousPageBtnSignUp)
        backtoLoginBtn.setOnClickListener {
            val intent1 = Intent(this, MainActivity::class.java)
            startActivity(intent1)
        }

        val userNameET = findViewById<EditText>(R.id.FullNameTxtBoxSignUp)
        val emailET = findViewById<EditText>(R.id.EmailTxtBoxSignUp)
        val passwordET = findViewById<EditText>(R.id.passwordTxtBoxSignUp)

        val signUpBtnHere = findViewById<Button>(R.id.signUpBtn)
        signUpBtnHere.setOnClickListener{
            val userName = userNameET.text.toString().trim()
            val email = emailET.text.toString().trim()
            val password = passwordET.text.toString().trim()

            // Check if any field is empty
            if (userName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                uploadUserToFirebase(userName, email, password)
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun uploadUserToFirebase(userName: String, email: String, password: String) {
        val usersRef = database.getReference("users")

        // Create a new instance of UserClass
        val userObj = UserClass()

        // Set user properties one by one
        userObj.userName = userName
        userObj.userEmail = email
        userObj.passWord = password

        // Push the user object to Firebase
        val newUserRef = usersRef.push()
        newUserRef.setValue(userObj)

        Toast.makeText(this, "User signed up successfully", Toast.LENGTH_SHORT).show()
    }
}

/*
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
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase
    var userObj = UserClass()

    private fun uploadTextToFirebase(yuusy: String) {
        val myRef = database.getReference("username").push()
        myRef.setValue(yuusy)
    }
    private fun uploadPasswordToFirebase(passy: String) {
        val myRef = database.getReference("password").push()
        myRef.setValue(passy)
    }
    private fun uploadEmailToFirebase(eemy: String) {
        val myRef = database.getReference("email").push()
        myRef.setValue(eemy)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //assigned a layout connection here
        setContentView(R.layout.activity_sign_up)

        // Initialize Firebase
        database = FirebaseDatabase.getInstance()
        val backtoLoginBtn = findViewById<Button>(R.id.previousPageBtnSignUp)
        backtoLoginBtn.setOnClickListener {
            val intent1 = Intent(this, MainActivity::class.java)
            startActivity(intent1)
        }

        //User saving function
        val userNameET = findViewById<EditText>(R.id.FullNameTxtBoxSignUp)
        val emailET = findViewById<EditText>(R.id.EmailTxtBoxSignUp)
        val passwordET = findViewById<EditText>(R.id.passwordTxtBoxSignUp)

        val signUpBtnHere = findViewById<Button>(R.id.signUpBtn)
        signUpBtnHere.setOnClickListener {
            userObj.userName = userNameET.text.toString()
            userObj.userEmail = emailET.text.toString()
            userObj.passWord = passwordET.text.toString()

            val usyname = userObj.userName
            val passyword = userObj.passWord
            val eemymail = userObj.userEmail

            uploadTextToFirebase(usyname)
            uploadPasswordToFirebase(passyword)
            uploadEmailToFirebase(eemymail)

            // No issue?? `,:(
            UserClass.userMutableList.add(userObj)
            Toast.makeText(this,"Username: "+UserClass.userMutableList[0].userName+" Email: "+UserClass.userMutableList[0].userEmail, Toast.LENGTH_SHORT).show()
        }
    }
}
*/