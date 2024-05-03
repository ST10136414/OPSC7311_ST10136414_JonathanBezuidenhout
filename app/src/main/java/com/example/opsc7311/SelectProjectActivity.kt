package com.example.opsc7311

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import Classes.ProjectClass
import Classes.UserClass
import androidx.core.content.ContextCompat

class SelectProjectActivity : AppCompatActivity() {
    private val userList = mutableListOf<UserClass>()
    private lateinit var territoryHeadingTxt: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_project)

        val projectNames = intent.getStringArrayListExtra("projectNames")
        if (projectNames != null) {
            displayProjects(projectNames)
        }
    }

    private fun displayProjects(projectNames: List<String>) {
        val projectsContainer = findViewById<LinearLayout>(R.id.projectsContainer)
        for (projectName in projectNames) {
            val projectNameTextView = TextView(this)
            projectNameTextView.text = projectName
            projectNameTextView.setTextColor(ContextCompat.getColor(this, android.R.color.white))
            projectsContainer.addView(projectNameTextView)
        }
    }
}
