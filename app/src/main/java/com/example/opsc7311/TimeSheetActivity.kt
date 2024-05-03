package com.example.opsc7311

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



class TimeSheetActivity : AppCompatActivity(){

    var tmshtEntryObj = EntryClass()
    var tmshtEntryObj1= EntryClass()
    var tmshtEntryObj2 = EntryClass()
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
                    val filteredList = EntryClass.entryMutableList.filter { it.user == UserClass.loggedUser.toString() }
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle case when nothing is selected (if needed)
            }
        }



        //val filteredList = EntryClass.timesheetMutableList.filter { it.user == UserClass.loggedUser.toString() }


        //timesheet dummy entries
        tmshtEntryObj = EntryClass("Product transport", "Theres a lot of stuff in it", 40,2,3 )
        tmshtEntryObj1 = EntryClass("task2", "Theres a lot of stuff in it", 40,2,3 )
        tmshtEntryObj2=EntryClass("task3", "Theres a lot of stuff in it", 40,2,3 )

        EntryClass.entryMutableList.add(tmshtEntryObj)
        EntryClass.entryMutableList.add(tmshtEntryObj1)
        EntryClass.entryMutableList.add(tmshtEntryObj2)

        //timesheet display functionality
        val tmsheetText = findViewById<TextView>(R.id.tvTimeSheetName)

        if (EntryClass.entryMutableList.isNullOrEmpty())
        {
            Toast.makeText(this,"No entries were found in the database", Toast.LENGTH_SHORT).show()
        }
        else {
            tmsheetText.text=""
            for (i in EntryClass.entryMutableList.indices) {

                tmsheetText.text = tmsheetText.text.toString() + "[" + EntryClass.entryMutableList[i].projectName + " ] \nNotes: " + EntryClass.entryMutableList[i].note +
                            "\nTime Logged: " + EntryClass.entryMutableList[i].loggedTime + "\nStartTime: " + EntryClass.entryMutableList[i].startTime+"\n \n"
            }
        }
    }
}


