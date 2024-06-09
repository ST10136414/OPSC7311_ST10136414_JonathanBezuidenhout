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
import com.google.firebase.database.*


class SignInActivity : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase
    private lateinit var usersRef: DatabaseReference

    var userObj = UserClass()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_in)

        //Toast.makeText(this,"Username: "+UserClass.userMutableList[0].userName+" Email: "+UserClass.userMutableList[0].userEmail, Toast.LENGTH_SHORT).show()
        // Initialize Firebase
        database = FirebaseDatabase.getInstance()
        usersRef = database.getReference("users")

        //Previous Page navigation
        val previousPageBtnAgain = findViewById<Button>(R.id.previousPageBtn)
        previousPageBtnAgain.setOnClickListener {
            val previousPageIntent = Intent(this, MainActivity::class.java)
            startActivity(previousPageIntent)
        }

        //ForgetPass Page navigation
        val forgotPassBtn = findViewById<Button>(R.id.forgotYourPasswordBtn)
        forgotPassBtn.setOnClickListener {
            val forgotPassIntent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(forgotPassIntent)
        }

        //Page Functionality
        val emailET = findViewById<EditText>(R.id.emailTxtBoxSignIn)
        val passET = findViewById<EditText>(R.id.passwordTxtBoxSignIn)

        val signInBtnAgain = findViewById<Button>(R.id.signInBtn)
        signInBtnAgain.setOnClickListener {
            val emailtext = emailET.text.toString()
            //Toast.makeText(this,"EmailText: "+emailtext, Toast.LENGTH_SHORT).show()

            val pass = passET.text.toString()

            //checks if anything is in the list
            if (UserClass.userMutableList.isNullOrEmpty())
            {
                Toast.makeText(this,"No Users were found in the database", Toast.LENGTH_SHORT).show()
            }
            else {
                for (i in UserClass.userMutableList.indices) {

                    if (emailtext == UserClass.userMutableList[i].userEmail.toString()) {
                        if (pass == UserClass.userMutableList[i].passWord.toString()) {
                            //toast(ThingFound), wait 2 seconds, then..
                            Toast.makeText(
                                this,
                                "Email and Password Found! Redirecting...",
                                Toast.LENGTH_SHORT
                            ).show()
                            UserClass.loggedUser= UserClass.userMutableList[i]
                            Thread.sleep(2_000)
                            val signInIntent = Intent(this, DashboardActivity::class.java)
                            startActivity(signInIntent)
                        } else {
                            Toast.makeText(
                                this,
                                "Password was not found, please try again",
                                Toast.LENGTH_SHORT
                            ).show()
                            emailET.text.clear()
                            passET.text.clear()
                        }
                    } else {
                        Toast.makeText(
                            this,
                            "Email was not found, please try again",
                            Toast.LENGTH_SHORT
                        ).show()
                        emailET.text.clear()
                        passET.text.clear()
                    }

                    //validate that user and pass exist
                    //if (user.string = i.username.toString())
                    //then
                    //toast(ThingFound), wait 2 seconds, then..

                }
            }


        }
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
import com.google.firebase.database.*

class SignInActivity : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase
    private lateinit var usersRef: DatabaseReference
    var userObj = UserClass()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_in)

        // Initialize Firebase
        database = FirebaseDatabase.getInstance()
        usersRef = database.getReference("users")

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
            val emailtext = emailET.text.toString().replace(".", "_") // Replace '.' with '_'
            val pass = passET.text.toString()

            if (emailtext.isNotEmpty() && pass.isNotEmpty()) {
                signInUser(emailtext, pass)
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun signInUser(email: String, password: String) {
        val userRef = usersRef.child(email)

        userRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val user = dataSnapshot.getValue(UserClass::class.java)
                    if (user != null && user.passWord == password) {
                        Toast.makeText(this@SignInActivity, "Email and Password Found! Redirecting...", Toast.LENGTH_SHORT).show()
                        UserClass.loggedUser = user
                        val signInIntent = Intent(this@SignInActivity, DashboardActivity::class.java)
                        startActivity(signInIntent)
                    } else {
                        Toast.makeText(this@SignInActivity, "Password was not found, please try again", Toast.LENGTH_SHORT).show()
                        clearFields()
                    }
                } else {
                    Toast.makeText(this@SignInActivity, "Email was not found, please try again", Toast.LENGTH_SHORT).show()
                    clearFields()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@SignInActivity, "Error: ${databaseError.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun clearFields() {
        findViewById<EditText>(R.id.emailTxtBoxSignIn).text.clear()
        findViewById<EditText>(R.id.passwordTxtBoxSignIn).text.clear()
    }
}
