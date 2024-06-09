package com.example.opsc7311

import Classes.EntryClass
import Classes.ProjectClass
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Manage_projects : AppCompatActivity() {

    lateinit var selectedProject: ProjectClass

    //val filteredList= mutableListOf(EntryClass)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_manage_projects)

        var filteredList = mutableListOf<EntryClass>()

        var thisProject = ProjectClass()



        val projectObj = ProjectClass()
        projectObj.projectName="OPSC"
        projectObj.budget="200"
        projectObj.hourlyRate="10"
        //projectObj.
        //page functionality
        //States the uSpinner items as userMutableList, specifically its usernames
        val spinnerItems = ProjectClass.projectMutableList.map{it.projectName}

        val pSpinner: Spinner = findViewById(R.id.projectSpinner)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerItems)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        pSpinner.adapter = adapter

        //to display


        pSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // Retrieve the selected username
                val chosenProjectName:String = spinnerItems[position]

                // Find the corresponding UserClass object
                val selectedProject = ProjectClass.projectMutableList.find { it.projectName == chosenProjectName}
                //thisProject = selectedProject

                // Now you can access the selected user's properties
                if (selectedProject != null) {
                    filteredList = EntryClass.entryMutableList.filter { it.selectedProjectName == chosenProjectName }.toMutableList()
                }

                // val project = ProjectClass.projectMutableList.find { it.projectName==selectedProjectName }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle case when nothing is selected (if needed)
            }
        }



        fun convertMinutesToHoursAndMinutes(totalMinutes: Int): String {
            val hours = totalMinutes / 60
            val minutes = totalMinutes % 60

            return "$hours hours $minutes minutes"
        }



       // val currentTotal = projectObj.totaltime.toInt()
        //val newTotal = convertMinutesToHoursAndMinutes(currentTotal)

        // convert int to hours and minutes


        //timesheet display functionality
        val detailsText = findViewById<TextView>(R.id.projectDetailsText)

        if (ProjectClass.projectMutableList.isNullOrEmpty())
        {
            Toast.makeText(this,"No projects were found in the database", Toast.LENGTH_SHORT).show()
        }
        else {
            detailsText.text=""
            for (i in ProjectClass.projectMutableList.indices) {
                detailsText.text = detailsText.text.toString()+"["+thisProject.projectName+"]\n"
                for (j in filteredList.indices) {

                    detailsText.text = detailsText.text.toString() +"-Time Entry"+ filteredList[i].selectedProjectName + " ] \nNotes: " + filteredList[i].note +
                            "\nTime Logged: " + filteredList[i].loggedTime + "\nStartTime: " + filteredList[i].startTime+"\n \n"
                }

            }
        }


    }
}