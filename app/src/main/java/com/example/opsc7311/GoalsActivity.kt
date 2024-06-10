package com.example.opsc7311

import Classes.GoalClass
import Classes.ProjectClass
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.media.Image
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.FirebaseDatabase
import org.w3c.dom.Text
import java.util.Calendar
import java.text.SimpleDateFormat
import java.util.Locale


class GoalsActivity : AppCompatActivity() {
    private lateinit var btnCreateNewGoal:Button
   // private lateinit var txtGoalName: TextView
    private lateinit var txtTodayMaxGoal: EditText
    private lateinit var txtTodayMinGoal: EditText
    private lateinit var txtWeekMaxGoal: EditText
    private lateinit var txtWeekMinGoal: EditText

    private lateinit var TodayDateStr: String
    private lateinit var WeekStartStr:String
    private lateinit var WeekEndStr: String

    private var cal = Calendar.getInstance()


    private lateinit var btnTodayDate: ImageView
    private lateinit var btnWeekStart: ImageView
    private lateinit var btnWeekEnd: ImageView

    private lateinit var database: FirebaseDatabase


    val goalsList = GoalClass.goalsMutableList
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_goals)
        btnCreateNewGoal = findViewById<Button>(R.id.btnAddGoals)

        txtTodayMinGoal = findViewById<EditText>(R.id.ETtodayMinGoal)
        txtTodayMaxGoal = findViewById<EditText>(R.id.ETtodayMaxGoal)

        txtWeekMinGoal = findViewById<EditText>(R.id.ETweekMinGoal)
        txtWeekMaxGoal = findViewById<EditText>(R.id.ETweekMaxGoal)

        btnTodayDate = findViewById<ImageView>(R.id.todayDatePicker)

        btnWeekStart = findViewById<ImageView>(R.id.WeekDatePicker1)
        btnWeekEnd = findViewById<ImageView>(R.id.WeekDatePicker2)




        val todayDateSetListener = object: DatePickerDialog.OnDateSetListener{
            override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                cal.set(Calendar.YEAR,year)
                cal.set(Calendar.MONTH,month)
                cal.set(Calendar.DAY_OF_MONTH,dayOfMonth)
                updateTodayDateStr()
            }
        }



        val weekStartDateSetListener = object: DatePickerDialog.OnDateSetListener{
            override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                cal.set(Calendar.YEAR,year)
                cal.set(Calendar.MONTH,month)
                cal.set(Calendar.DAY_OF_MONTH,dayOfMonth)
                updateWeekStartStr()
            }
        }

        val weekEndDateSetListener = object: DatePickerDialog.OnDateSetListener{
            override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                cal.set(Calendar.YEAR,year)
                cal.set(Calendar.MONTH,month)
                cal.set(Calendar.DAY_OF_MONTH,dayOfMonth)
                updateWeekEndStr()
            }
        }


        //Date Button Actions
        btnTodayDate.setOnClickListener()
        {
            DatePickerDialog(this@GoalsActivity,weekStartDateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }



        btnWeekStart.setOnClickListener()
        {
            DatePickerDialog(this@GoalsActivity,weekStartDateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }

        btnWeekEnd.setOnClickListener()
        {
            DatePickerDialog(this@GoalsActivity,weekEndDateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }



        btnCreateNewGoal.setOnClickListener()
        {
            val newGoalobj = GoalClass()
            newGoalobj.maxGoalToday = txtTodayMaxGoal.text.toString()
            newGoalobj.minGoalToday = txtTodayMinGoal.text.toString()
            newGoalobj.maxGoalWeek = txtWeekMaxGoal.text.toString()
            newGoalobj.minGoalWeek = txtWeekMinGoal.text.toString()

            /*newGoalobj.TodayDate= TodayDateStr
            newGoalobj.WeekStartDate = WeekStartStr
            newGoalobj.WeekEndDate = WeekEndStr*/


            uploadGoalToFirebase(newGoalobj.maxGoalToday,newGoalobj.minGoalToday,newGoalobj.maxGoalWeek,newGoalobj.minGoalWeek)

            goalsList.add(newGoalobj)
            txtTodayMaxGoal.text.clear()
            txtTodayMinGoal.text.clear()
            txtWeekMinGoal.text.clear()
            txtWeekMaxGoal.text.clear()

            //Toast.makeText(this, "Goal Saved!", Toast.LENGTH_SHORT).show()

        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }


    private fun updateTodayDateStr()
    {
        val myFormat = "MM/dd/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        TodayDateStr = sdf.format(this.cal.time)
    }

    private fun updateWeekStartStr()
    {
        val myFormat = "MM/dd/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        WeekStartStr = sdf.format(this.cal.time)
    }

    private fun updateWeekEndStr()
    {
        val myFormat = "MM/dd/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        WeekEndStr = sdf.format(this.cal.time)
    }

    private fun uploadGoalToFirebase(todayMaxGoal: String, todayMinGoal: String, weekMaxGoal: String, weekMinGoal: String) {
        val goalObj = GoalClass()

        goalObj.maxGoalToday = todayMaxGoal
        goalObj.minGoalToday = todayMinGoal
        goalObj.maxGoalWeek = weekMaxGoal
        goalObj.minGoalWeek= weekMinGoal

        database.getReference("goals").push().setValue(goalObj)
        Toast.makeText(this, "Goal added successfully", Toast.LENGTH_SHORT).show()
    }







}