package com.example.opsc7311

import Classes.ProjectClass
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CreateProject : AppCompatActivity() {
    private var projectClass = ProjectClass()
    private var cal = Calendar.getInstance()
    private lateinit var etProjectName:EditText
    private lateinit var etProjectColour:EditText
    private lateinit var etClientName:EditText
    private lateinit var etStartDate:TextView
    private lateinit var etEndDate :TextView
    private lateinit var etBudget:EditText
    private lateinit var etHourlyRate:EditText
    private lateinit var dateChosen: TextView
    private lateinit var btnCreateProject: Button
    //this may not be global
    val projectList = ProjectClass.projectMutableList
    //-----------------------------------------------------//
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create_project)
        etProjectName = findViewById<EditText>(R.id.editTxtMinGoal)
        //etProjectColour = findViewById<EditText>(R.id.editTxtProj)
        etClientName = findViewById<EditText>(R.id.editTxtClientName)
        etStartDate = findViewById<TextView>(R.id.txtStartDate)
        etEndDate = findViewById<TextView>(R.id.txtEndDate)
        etBudget = findViewById<EditText>(R.id.editTxtBudget)
        etHourlyRate = findViewById<EditText>(R.id.editTxtHourlyRate)
        btnCreateProject = findViewById<Button>(R.id.btnCreateProject)




        val startDateSetListener = object: DatePickerDialog.OnDateSetListener{
            override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                cal.set(Calendar.YEAR,year)
                cal.set(Calendar.MONTH,month)
                cal.set(Calendar.DAY_OF_MONTH,dayOfMonth)
                updateStartDateEditText()
            }
        }

        val endDateSetListener = object: DatePickerDialog.OnDateSetListener{
            override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                cal.set(Calendar.YEAR,year)
                cal.set(Calendar.MONTH,month)
                cal.set(Calendar.DAY_OF_MONTH,dayOfMonth)
                updateEndDateEditText()
            }
        }

        btnCreateProject.setOnClickListener()
        {
            val newProject = projectClass
            newProject.projectName = etProjectName.text.toString()
            newProject.clientName = etClientName.text.toString()
            newProject.startDate = etStartDate.text.toString()
            newProject.endDate = etEndDate.text.toString()
            newProject.budget = etBudget.text.toString()
            newProject.hourlyRate = etHourlyRate.text.toString()
            projectList.add(newProject)
            val listSize = projectList.size
            etProjectName.text.clear()
            //etProjectColour = findViewById<EditText>(R.id.editTxtProj)
            etClientName.text.clear()
            etStartDate.text = ""
            etEndDate.text = ""
            etBudget.text.clear()
            etHourlyRate.text.clear()
            Toast.makeText(this, listSize.toString(), Toast.LENGTH_SHORT).show()
            val intent = Intent(this, DashboardActivity::class.java)
            //
            val projectNamesList = ArrayList<String>()
            for (project in projectList) {
                projectNamesList.add(project.projectName)
            }
            intent.putStringArrayListExtra("projectNames", projectNamesList)
            startActivity(intent)
        }

        etStartDate.setOnClickListener()
        {
            DatePickerDialog(this@CreateProject,startDateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }

        etEndDate.setOnClickListener()
        {
            DatePickerDialog(this@CreateProject,endDateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }

    //-----------------------------------------------------//
    //Function to update the start date TextView
    private fun updateStartDateEditText()
    {
        val myFormat = "MM/dd/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        etStartDate.text = sdf.format(this.cal.time)
    }
    //-----------------------------------------------------//
    //Function to update The End Date TextView
    private fun updateEndDateEditText()
    {
        val myFormat = "MM/dd/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        etEndDate.text = sdf.format(this.cal.time)
    }
    //-----------------------------------------------------//
}
//-------------------------------------------END OF FILE----------------------------------------------//