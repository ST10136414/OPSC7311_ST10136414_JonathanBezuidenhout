package com.example.opsc7311

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

class ProfileActivity : AppCompatActivity() {
    private lateinit var profileImageButton: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)

        val userTxt = findViewById<TextView>(R.id.txtUsername)

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
}