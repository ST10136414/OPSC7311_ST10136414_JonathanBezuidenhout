package com.example.opsc7311

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)

        val createProjBtn = findViewById<ImageView>(R.id.createProjectBtn)
        createProjBtn.setOnClickListener {
            val crProjIntent = Intent(this, CreateProject::class.java)
            startActivity(crProjIntent)
        }

        val inviteMemberBtn = findViewById<ImageView>(R.id.InviteMemberBtn)
        inviteMemberBtn.setOnClickListener {
            Toast.makeText(this,"This opens the invite member page", Toast.LENGTH_SHORT).show()
        }

        val viewReportBtn = findViewById<ImageView>(R.id.ViewReportBtn)
        viewReportBtn.setOnClickListener {
            Toast.makeText(this,"This opens the view report page", Toast.LENGTH_SHORT).show()
        }


        //nav bar elements
        //Navigates to timesheet
        val navTimShtBtn = findViewById<ImageView>(R.id.navBtnTimeSheet)
        navTimShtBtn.setOnClickListener{
            val timeSheetIntent = Intent(this, TimeSheetActivity::class.java)
            startActivity(timeSheetIntent)
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

    }
}