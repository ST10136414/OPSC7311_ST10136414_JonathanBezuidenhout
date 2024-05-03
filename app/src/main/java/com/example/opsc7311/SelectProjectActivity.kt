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

        territoryHeadingTxt = findViewById(R.id.projectsContainer) // Initialize your LinearLayout

        // Populate userList with dummy data (replace with  actual data retrieval logic)
        val user1 = UserClass("Paul")
        val user2 = UserClass("John")
        val project1 = ProjectClass().apply { projectName = "Banking App" }
        val project2 = ProjectClass().apply { projectName = "Communications" }
        val project3 = ProjectClass().apply { projectName = "General" }

        user1.addProject(project1)
        user1.addProject(project2)
        user1.addProject(project3)

        userList.add(user1)
        userList.add(user2)

        displayProjects(userList)
    }

    private fun displayProjects(users: List<UserClass>) {
        for (user in users) {
            val userNameTextView = TextView(this)
            userNameTextView.text = user.userName
            userNameTextView.setTextColor(ContextCompat.getColor(this, android.R.color.white))
            territoryHeadingTxt.addView(userNameTextView)

            for (project in user.projects) {
                val projectNameTextView = TextView(this)
                projectNameTextView.text = project.projectName
                userNameTextView.setTextColor(ContextCompat.getColor(this, android.R.color.white))
                territoryHeadingTxt.addView(projectNameTextView)
            }
        }
    }
}
