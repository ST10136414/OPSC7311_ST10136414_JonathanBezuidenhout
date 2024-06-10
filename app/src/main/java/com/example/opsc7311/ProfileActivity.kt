package com.example.opsc7311

import Classes.GoalClass
import Classes.UserClass
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.ImageView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ProfileActivity : AppCompatActivity() {
    private lateinit var profileImageButton: ImageView
    private lateinit var database: FirebaseDatabase
    private lateinit var profileRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)

        database = FirebaseDatabase.getInstance()
        profileRef = database.getReference("users")


        val currentUser = UserClass.loggedUser

        val userTxt = findViewById<TextView>(R.id.txtUsername)

        //code displays username
        if (currentUser != null)
        {
            userTxt.text = currentUser.userName
        }
        else
        {
            userTxt.text = "User Profile Not Found"
        }

        if (UserClass.userMutableList.isNullOrEmpty()) {
        } else {
            userTxt.text = UserClass.loggedUser.userName.toString()
        }

        val sgnOutBtn = findViewById<Button>(R.id.btnSignOut)
        sgnOutBtn.setOnClickListener {
            var emptyUser = UserClass("", "", "")
            val sgnOutIntent = Intent(this, MainActivity::class.java)
            UserClass.loggedUser = emptyUser
            startActivity(sgnOutIntent)
        }

        profileImageButton = findViewById(R.id.btnProfileImage)
        profileImageButton.setOnClickListener {
            checkCameraPermission()
        }



        val backBtn = findViewById<ImageView>(R.id.backBtn3)
        backBtn.setOnClickListener{
            val DashboardIntent = Intent(this, DashboardActivity::class.java)
            startActivity(DashboardIntent)
        }



    }

    private fun checkCameraPermission() {
        if (checkSelfPermission(android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(android.Manifest.permission.CAMERA), CAMERA_PERMISSION_CODE)
        } else {
            openCamera()
        }
    }

    private val cameraActivityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val imageBitmap = result.data?.extras?.get("data") as Bitmap
                profileImageButton.setImageBitmap(imageBitmap)
                // You can save the image bitmap to storage or upload it to a server
            } else {
                Toast.makeText(this, "Failed to capture image", Toast.LENGTH_SHORT).show()
            }
        }

    private fun openCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraActivityResultLauncher.launch(cameraIntent)
    }

    companion object {
        private const val CAMERA_PERMISSION_CODE = 101
    }

/*
    private fun loadProfileData() {
        val loggedUserName = UserClass.loggedUser.userName

        profileRef.orderByChild("userName").equalTo(loggedUserName).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (userSnapshot in dataSnapshot.children) {
                        val user = userSnapshot.getValue(UserClass::class.java)
                        if (user != null) {
                            findViewById<TextView>(R.id.txtUsername).text = user.userName
                            //findViewById<TextView>(R.id.txtEmail).text = user.userEmail
                            //findViewById<TextView>(R.id.txtPassword).text = user.passWord
                        }
                    }
                } else {
                    Toast.makeText(this@ProfileActivity, "User not found", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@ProfileActivity, "Error: ${databaseError.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }*/





}