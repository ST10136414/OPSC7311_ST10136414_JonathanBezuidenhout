/*package com.example.opsc7311

import Classes.EntryClass
import Classes.UserClass
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

//import com.example.opsc7311.databinding.ActivityTimeSheetBinding
//i want to test github


class TimeSheetActivity : AppCompatActivity(){

    //private lateinit var calendarButton: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_time_sheet)

        //navigation section
        //Navigates to the calendar
        val calendarButton = findViewById<ImageView>(R.id.CalendarButton)
        calendarButton.setOnClickListener {
            val calIntent = Intent(this, CalendarActivity::class.java)
            startActivity(calIntent)
        }
        //Navigates to dashboard
        val navDashBtn = findViewById<ImageView>(R.id.navBtnDash)
        navDashBtn.setOnClickListener{
            val dashIntent = Intent(this, DashboardActivity::class.java)
            startActivity(dashIntent)
        }
        //navigates to timer page
        val navTmrBtn = findViewById<ImageView>(R.id.navBtnTimer)
        navTmrBtn.setOnClickListener{
            val tmrIntent = Intent(this, TimerActivity::class.java)
            startActivity(tmrIntent)
        }

        //navigates to More Options page
        val navMreBtn = findViewById<ImageView>(R.id.navBtnMore)
        navMreBtn.setOnClickListener {
            val navMreIntent = Intent(this, MoreOptionsActivity::class.java)
            startActivity(navMreIntent)
        }

        //navigates to report page
        val navReportBtn = findViewById<ImageView>(R.id.navBtnReport)
        navReportBtn.setOnClickListener {
            Toast.makeText(this,"This opens the view report page", Toast.LENGTH_SHORT).show()
        }


        val newEntryBtn = findViewById<ImageView>(R.id.newEntryBtn)
        newEntryBtn.setOnClickListener{
            val entryIntent = Intent(this, NewEntryActivity::class.java)
            startActivity(entryIntent)
        }

        //val userArray = resources.getStringArray(R.array.user_array)

        //page functionality
        //States the uSpinner items as userMutableList, specifically its usernames
        val spinnerItems = UserClass.userMutableList.map{it.userName}

        val uSpinner: Spinner = findViewById(R.id.user_spinner)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerItems)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        uSpinner.adapter = adapter


        uSpinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // Retrieve the selected username
                val selectedUserName = spinnerItems[position]

                // Find the corresponding UserClass object
                val selectedUser = UserClass.userMutableList.find { it.userName == selectedUserName }

                // Now you can access the selected user's properties
                if (selectedUser != null) {
                    val filteredList = EntryClass.entryMutableList.filter { it.selectedProjectName == UserClass.loggedUser.toString() }
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle case when nothing is selected (if needed)
            }
        }

        //val filteredList = EntryClass.timesheetMutableList.filter { it.user == UserClass.loggedUser.toString() }


        //timesheet display functionality
        val tmsheetText = findViewById<TextView>(R.id.tvTimeSheetName)

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
}*/
/*
package com.example.opsc7311

import Classes.EntryClass
import Classes.ProjectClass
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.*

class TimeSheetActivity : AppCompatActivity() {
    private lateinit var database: FirebaseDatabase
    private lateinit var projectsRef: DatabaseReference
    private lateinit var entriesTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_time_sheet)

        // Initialize Firebase
        database = FirebaseDatabase.getInstance()
        projectsRef = database.getReference("projects")

        entriesTextView = findViewById(R.id.tvTimeSheetName)

        // Load entries from Firebase
        loadEntriesFromFirebase()

        // Navigation section
        setupNavigation()

        // Initialize spinner with user names
        setupUserSpinner()
    }

    private fun setupNavigation() {
        findViewById<ImageView>(R.id.CalendarButton).setOnClickListener {
            startActivity(Intent(this, CalendarActivity::class.java))
        }
        findViewById<ImageView>(R.id.navBtnDash).setOnClickListener {
            startActivity(Intent(this, DashboardActivity::class.java))
        }
        findViewById<ImageView>(R.id.navBtnTimer).setOnClickListener {
            startActivity(Intent(this, TimerActivity::class.java))
        }
        findViewById<ImageView>(R.id.navBtnMore).setOnClickListener {
            startActivity(Intent(this, MoreOptionsActivity::class.java))
        }
        findViewById<ImageView>(R.id.navBtnReport).setOnClickListener {
            Toast.makeText(this, "This opens the view report page", Toast.LENGTH_SHORT).show()
        }
        findViewById<ImageView>(R.id.newEntryBtn).setOnClickListener {
            startActivity(Intent(this, NewEntryActivity::class.java))
        }
    }

    private fun setupUserSpinner() {
        val spinnerItems = listOf("All Users") // Modify this to include actual user names if needed

        val uSpinner: Spinner = findViewById(R.id.user_spinner)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerItems)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        uSpinner.adapter = adapter

        uSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedProjectName = spinnerItems[position]
                loadEntriesFromFirebase() // Pass the selected project name here
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle case when nothing is selected (if needed)
            }
        }


    }

    //old and stinky!

    private fun loadEntriesFromFirebase() {
        projectsRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val entriesList = mutableListOf<EntryClass>()
                for (projectSnapshot in dataSnapshot.children) {
                    val projectEntries = projectSnapshot.child("entries").children
                    for (entrySnapshot in projectEntries) {
                        val entry = entrySnapshot.getValue(EntryClass::class.java)
                        entry?.let { entriesList.add(it) }
                    }
                }
                displayEntries(entriesList)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@TimeSheetActivity, "Failed to load entries", Toast.LENGTH_SHORT).show()
            }
        })
    }
    //new and fresh
    /*
    private fun loadEntriesFromFirebase(projectName: String) {
        projectsRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val entriesList = mutableListOf<EntryClass>()
                for (projectSnapshot in dataSnapshot.children) {
                    if (projectSnapshot.key == projectName) {
                        val projectEntries = projectSnapshot.child("entries").children
                        for (entrySnapshot in projectEntries) {
                            val entry = entrySnapshot.getValue(EntryClass::class.java)
                            entry?.let { entriesList.add(it) }
                        }
                        break // Stop after finding the selected project
                    }
                }
                displayEntries(entriesList)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@TimeSheetActivity, "Failed to load entries", Toast.LENGTH_SHORT).show()
            }
        })
    }*/




    private fun displayEntries(entriesList: List<EntryClass>) {
        if (entriesList.isEmpty()) {
            Toast.makeText(this, "No entries found in the database", Toast.LENGTH_SHORT).show()
        } else {
            val stringBuilder = StringBuilder()
            for (entry in entriesList) {
                stringBuilder.append("[${entry.selectedProjectName}] \nNotes: ${entry.note}\nTime Logged: ${entry.loggedTime}\nStartTime: ${entry.startTime}\n\n")
            }
            entriesTextView.text = stringBuilder.toString()
        }
    }
}
*/
package com.example.opsc7311

import Classes.EntryClass
import Classes.ProjectClass
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.*

class TimeSheetActivity : AppCompatActivity() {
    private lateinit var database: FirebaseDatabase
    private lateinit var projectsRef: DatabaseReference
    private lateinit var entriesTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_time_sheet)

        // Initialize Firebase
        database = FirebaseDatabase.getInstance()
        projectsRef = database.getReference("projects")

        entriesTextView = findViewById(R.id.tvTimeSheetName)

        // Load entries from Firebase
        loadEntriesFromFirebase()

        // Navigation section
        setupNavigation()

        // Initialize spinner with user names
        setupUserSpinner()
    }

    private fun setupNavigation() {
        findViewById<ImageView>(R.id.CalendarButton).setOnClickListener {
            startActivity(Intent(this, CalendarActivity::class.java))
        }
        findViewById<ImageView>(R.id.navBtnDash).setOnClickListener {
            startActivity(Intent(this, DashboardActivity::class.java))
        }
        findViewById<ImageView>(R.id.navBtnTimer).setOnClickListener {
            startActivity(Intent(this, TimerActivity::class.java))
        }
        findViewById<ImageView>(R.id.navBtnMore).setOnClickListener {
            startActivity(Intent(this, MoreOptionsActivity::class.java))
        }
        findViewById<ImageView>(R.id.navBtnReport).setOnClickListener {
            Toast.makeText(this, "This opens the view report page", Toast.LENGTH_SHORT).show()
        }
        findViewById<ImageView>(R.id.newEntryBtn).setOnClickListener {
            startActivity(Intent(this, NewEntryActivity::class.java))
        }
    }

    private fun setupUserSpinner() {
        val spinnerItems = mutableListOf<String>()

        val uSpinner: Spinner = findViewById(R.id.user_spinner)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerItems)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        uSpinner.adapter = adapter

        // Load project names from Firebase
        loadProjectNamesFromFirebase(spinnerItems, adapter)

        uSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedProjectName = spinnerItems[position]
                loadEntriesFromFirebase(selectedProjectName) // Pass the selected project name here
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle case when nothing is selected (if needed)
            }
        }
    }

    private fun loadProjectNamesFromFirebase(spinnerItems: MutableList<String>, adapter: ArrayAdapter<String>) {
        projectsRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (projectSnapshot in dataSnapshot.children) {
                    val projectName = projectSnapshot.child("projectName").value.toString()
                    spinnerItems.add(projectName)
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@TimeSheetActivity, "Failed to load project names", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun loadEntriesFromFirebase(projectName: String = "All Projects") {
        projectsRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val entriesList = mutableListOf<EntryClass>()
                for (projectSnapshot in dataSnapshot.children) {
                    if (projectName == "All Projects" || projectSnapshot.child("projectName").value == projectName) {
                        val projectEntries = projectSnapshot.child("entries").children
                        for (entrySnapshot in projectEntries) {
                            val entry = entrySnapshot.getValue(EntryClass::class.java)
                            entry?.let { entriesList.add(it) }
                        }
                    }
                }
                displayEntries(entriesList)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@TimeSheetActivity, "Failed to load entries", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun displayEntries(entriesList: List<EntryClass>) {
        if (entriesList.isEmpty()) {
            entriesTextView.text = ""
            Toast.makeText(this, "No entries found in the database", Toast.LENGTH_SHORT).show()
        } else {
            val stringBuilder = StringBuilder()
            for (entry in entriesList) {
                stringBuilder.append("[${entry.selectedProjectName}] \nNotes: ${entry.note}\nTime Logged: ${entry.loggedTime}\nStartTime: ${entry.startTime}\n\n")
            }
            entriesTextView.text = stringBuilder.toString()
        }
    }
}
