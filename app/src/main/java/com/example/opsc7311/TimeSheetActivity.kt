package com.example.opsc7311

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
//import com.example.opsc7311.databinding.ActivityTimeSheetBinding

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
        /* These pages do not exist
        val navReportBtn = findViewById<ImageView>(R.id.navBtnReport)
        navReportBtn.setOnClickListener{
            val repIntent = Intent(this,/*ReportActivity does not exist*/ )
        }*/
        /*
        val navMoreBtn = findViewById<ImageView>(R.id.navBtnMore)
        navMoreBtn.setOnClickListener{
            val moreIntent = Intent(this,/*the more page does not exist*/ )
        }*/

        val newEntryBtn = findViewById<ImageView>(R.id.newEntryBtn)
        newEntryBtn.setOnClickListener{
            val entryIntent = Intent(this, NewEntryActivity::class.java)
            startActivity(entryIntent)
        }


        //page functionality
        val uSpinner: Spinner = findViewById(R.id.user_spinner)
        val adapter = ArrayAdapter.createFromResource(this, R.array.user_array, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        uSpinner.adapter = adapter

    }
}


