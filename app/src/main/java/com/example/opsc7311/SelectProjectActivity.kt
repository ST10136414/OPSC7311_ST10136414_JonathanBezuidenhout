package com.example.opsc7311


import Classes.ProjectClass
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
    //private val userList = mutableListOf<UserClass>()
    //private lateinit var territoryHeadingTxt: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_select_project)

        // Retrieve project list from intent extras
        val projectList = intent.getParcelableArrayListExtra<ProjectClass>("projectList")

        // Extract project names
        val projectNames = projectList?.map { it.projectName }

        // Display projects
        if (projectNames != null) {
            displayProjects(projectNames)
        }

        // Initialize button outside of the displayProjects function
        val button1 = findViewById<Button>(R.id.button)
        button1?.setOnClickListener {
            val intent1 = Intent(this, DashboardActivity::class.java)
            startActivity(intent1)
        }
    }

    /*
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContentView(R.layout.activity_select_project)


                // Retrieve project list from intent extras
                val projectList = intent.getParcelableArrayListExtra<ProjectClass>("projectList")

                // Extract project names
                val projectNames = projectList?.map { it.projectName }

                // Display projects
                if (projectNames != null) {
                    displayProjects(projectNames)
                }

                // Initialize button outside of the displayProjects function
                val button1 = findViewById<Button>(R.id.button)
                button1?.setOnClickListener {
                    val intent1 = Intent(this, DashboardActivity::class.java)
                    startActivity(intent1)
                }
            }*/

    /*
                // Call the displayProjects function passing a list of project names
                displayProjects(listOf("Project A", "Project B", "Project C","Project D"))

                // Initialize button outside of the displayProjects function
                val button1 = findViewById<Button>(R.id.button)
                button1?.setOnClickListener {
                    val intent1 = Intent(this, DashboardActivity::class.java)
                    startActivity(intent1)
                }*//*
        // Retrieve project names from intent extras
        val projectNames = intent.getStringArrayListExtra("projectNames")

        // Display projects
        if (projectNames != null) {
            displayProjects(projectNames)
        }

        // Initialize button outside of the displayProjects function
        val button1 = findViewById<Button>(R.id.button)
        button1?.setOnClickListener {
            val intent1 = Intent(this, DashboardActivity::class.java)
            startActivity(intent1)
        }

    */

    private fun displayProjects(projectNames: List<String>) {
        val projectsContainer = findViewById<LinearLayout>(R.id.projectsNameContainer)

        // Get the current project names displayed in the container
        val currentProjectNames = mutableListOf<String>()
        for (i in 0 until projectsContainer.childCount) {
            val child = projectsContainer.getChildAt(i)
            if (child is TextView) {
                currentProjectNames.add(child.text.toString())
            }
        }

        // Add new project names that are not already displayed
        for (projectName in projectNames) {
            if (!currentProjectNames.contains(projectName)) {
                val projectNameTextView = TextView(this)
                projectNameTextView.text = projectName
                projectNameTextView.setTextColor(
                    ContextCompat.getColor(
                        this,
                        android.R.color.white
                    )
                )
                projectsContainer.addView(projectNameTextView)
                currentProjectNames.add(projectName) // Update currentProjectNames after adding new project name
            }
        }
    }
}
/*
import Classes.ProjectClass
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

        private fun displayProjects(projectNames: List<String>) {
            val projectsContainer = findViewById<LinearLayout>(R.id.projectsNameContainer)

            // Get the current project names displayed in the container
            val currentProjectNames = mutableListOf<String>()
            for (i in 0 until projectsContainer.childCount) {
                val child = projectsContainer.getChildAt(i)
                if (child is TextView) {
                    currentProjectNames.add(child.text.toString())
                }
            }

            // Add new project names that are not already displayed
            for (projectName in projectNames) {
                if (!currentProjectNames.contains(projectName)) {
                    val projectNameTextView = TextView(this)
                    projectNameTextView.text = projectName
                    projectNameTextView.setTextColor(
                        ContextCompat.getColor(
                            this,
                            android.R.color.white
                        )
                    )
                    projectsContainer.addView(projectNameTextView)
                    currentProjectNames.add(projectName) // Update currentProjectNames after adding new project name
                }
            }

            // Initialize button outside of the loop
            val button1 = findViewById<Button>(R.id.button)
            button1?.setOnClickListener {
                val intent1 = Intent(this, DashboardActivity::class.java)
                startActivity(intent1)
            }
        }
    }
}*/
       /* val projectList = intent.getParcelableArrayListExtra<ProjectClass>("projectList")
        if (projectList != null) {
            displayProjects(projectList)
        }
    }
/*
    private fun displayProjects(projectList: List<ProjectClass>) {
        val projectsContainer = findViewById<LinearLayout>(R.id.projectsNameContainer)
        projectsContainer.removeAllViews() // Clear existing views

        for ((index, project) in projectList.withIndex()) {
            val projectNameTextView = TextView(this)
            projectNameTextView.text = "Project ${index + 1}: ${project.projectName}"
            projectNameTextView.setTextColor(ContextCompat.getColor(this, android.R.color.white))
            projectsContainer.addView(projectNameTextView)
        }

        val button1 = findViewById<Button>(R.id.button)
        button1?.setOnClickListener {
            val intent1 = Intent(this, DashboardActivity::class.java)
            startActivity(intent1)
        }
    }
}
        val projectNames = intent.getStringArrayListExtra("projectNames")
        if (projectNames != null) {
            displayProjects(projectNames)
        }
    }

    private fun displayProjects(projectList: List<ProjectClass>) {
        val projectsContainer = findViewById<LinearLayout>(R.id.projectsNameContainer)
        projectsContainer.removeAllViews() // Clear existing views

        for ((index, project) in projectList.withIndex()) {
            val projectNameTextView = TextView(this)
            projectNameTextView.text = "Project ${index + 1}: ${project.projectName}"
            projectNameTextView.setTextColor(ContextCompat.getColor(this, android.R.color.white))
            projectsContainer.addView(projectNameTextView)
        }

        val button1 = findViewById<Button>(R.id.button)
        button1?.setOnClickListener {
            val intent1 = Intent(this, DashboardActivity::class.java)
            startActivity(intent1)
        }
    }

}*/


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
        */
