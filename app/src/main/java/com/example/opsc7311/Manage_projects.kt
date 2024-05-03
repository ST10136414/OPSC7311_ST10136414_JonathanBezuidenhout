package com.example.opsc7311

import Classes.EntryClass
import Classes.ProjectClass
import Classes.UserClass
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Manage_projects : AppCompatActivity() {
    val filteredList = mutableListOf<EntryClass>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_manage_projects)



        //page functionality
        //States the uSpinner items as userMutableList, specifically its usernames
        val spinnerItems = ProjectClass.projectMutableList.map{it.projectName}

        val pSpinner: Spinner = findViewById(R.id.projectSpinner)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerItems)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        pSpinner.adapter = adapter

        //to display


/*
        pSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // Retrieve the selected username
                val selectedProjectName = spinnerItems[position]

                // Find the corresponding UserClass object
                val selectedProject = ProjectClass.projectMutableList.find { it.projectName == selectedProjectName }

                // Now you can access the selected user's properties
                if (selectedProject != null) {
                    filteredList = EntryClass.entryMutableList.filter { it.selectedProjectName == selectedProject.projectName.toString() }
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle case when nothing is selected (if needed)
            }
        }

 */

        //timesheet display functionality
        val tmsheetText = findViewById<TextView>(R.id.timesheetText)

        if (EntryClass.entryMutableList.isNullOrEmpty())
        {
            Toast.makeText(this,"No entries were found in the database", Toast.LENGTH_SHORT).show()
        }
        else {
            tmsheetText.text=""
            for (i in EntryClass.entryMutableList.indices) {

                tmsheetText.text = tmsheetText.text.toString() + "[" + EntryClass.entryMutableList[i].selectedProjectName + " ] \nNotes: " + EntryClass.entryMutableList[i].note +
                        "\nTime Logged: " + EntryClass.entryMutableList[i].loggedTime + "\nStartTime: " + EntryClass.entryMutableList[i].startTime+"\n \n"
            }
        }


    }
}