package com.example.opsc7311

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import Classes.UserClass
import android.content.Intent
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.core.content.ContextCompat

class SelectProjectActivity : AppCompatActivity() {
    private val userList = mutableListOf<UserClass>()
    private lateinit var territoryHeadingTxt: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_select_project)



        val projectNames = intent.getStringArrayListExtra("projectNames")
        if (projectNames != null) {
            displayProjects(projectNames)
        }
    }
    private fun displayProjects(projectNames: List<String>) {
        val projectsContainer = findViewById<LinearLayout>(R.id.projectsNameContainer)

        // Clear existing views to avoid duplicates
        projectsContainer.removeAllViews()

        for (projectName in projectNames) {
            val projectNameTextView = TextView(this)
            projectNameTextView.text = projectName
            projectNameTextView.setTextColor(ContextCompat.getColor(this, android.R.color.white))

            // Add a line break after each project name
            projectNameTextView.append("\n")

            projectsContainer.addView(projectNameTextView)
        }

        // Initialize button outside of the loop
        val button1 = findViewById<Button>(R.id.button)
        button1?.setOnClickListener {
            val intent1 = Intent(this, DashboardActivity::class.java)
            startActivity(intent1)
        }
    }
}


    /*
        private fun displayProjects(projectNames: List<String>) {
            val projectsContainer = findViewById<LinearLayout>(R.id.projectsNameContainer)
            for (projectName in projectNames) {
                val projectNameTextView = TextView(this)
                projectNameTextView.text = projectName
                projectNameTextView.setTextColor(ContextCompat.getColor(this, android.R.color.white))
                projectsContainer.addView(projectNameTextView)
            }
        }*/

