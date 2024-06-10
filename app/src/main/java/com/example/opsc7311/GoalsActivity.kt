package com.example.opsc7311

import Classes.GoalClass
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class GoalsActivity : AppCompatActivity() {
    private lateinit var btnCreateNewGoal: Button
    private lateinit var txtTodayMaxGoal: EditText
    private lateinit var txtTodayMinGoal: EditText
    private lateinit var txtWeekMaxGoal: EditText
    private lateinit var txtWeekMinGoal: EditText

    private lateinit var TodayDateStr: String
    private lateinit var WeekStartStr: String
    private lateinit var WeekEndStr: String

    private var cal = Calendar.getInstance()

    private lateinit var btnTodayDate: ImageView
    private lateinit var btnWeekStart: ImageView
    private lateinit var btnWeekEnd: ImageView

    private lateinit var database: FirebaseDatabase

    private val goalsList = GoalClass.goalsMutableList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_goals)

        btnCreateNewGoal = findViewById(R.id.btnAddGoals)
        txtTodayMinGoal = findViewById(R.id.ETtodayMinGoal)
        txtTodayMaxGoal = findViewById(R.id.ETtodayMaxGoal)
        txtWeekMinGoal = findViewById(R.id.ETweekMinGoal)
        txtWeekMaxGoal = findViewById(R.id.ETweekMaxGoal)
        btnTodayDate = findViewById(R.id.todayDatePicker)
        btnWeekStart = findViewById(R.id.WeekDatePicker1)
        btnWeekEnd = findViewById(R.id.WeekDatePicker2)

        database = FirebaseDatabase.getInstance()

        val todayDateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, month)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateTodayDateStr()
        }

        val weekStartDateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, month)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateWeekStartStr()
        }

        val weekEndDateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, month)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateWeekEndStr()
        }

        btnTodayDate.setOnClickListener {
            DatePickerDialog(this@GoalsActivity, todayDateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }

        btnWeekStart.setOnClickListener {
            DatePickerDialog(this@GoalsActivity, weekStartDateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }

        btnWeekEnd.setOnClickListener {
            DatePickerDialog(this@GoalsActivity, weekEndDateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }

        btnCreateNewGoal.setOnClickListener {
            val newGoalObj = GoalClass().apply {
                maxGoalToday = txtTodayMaxGoal.text.toString()
                minGoalToday = txtTodayMinGoal.text.toString()
                maxGoalWeek = txtWeekMaxGoal.text.toString()
                minGoalWeek = txtWeekMinGoal.text.toString()
            }

            uploadGoalToFirebase(newGoalObj.maxGoalToday, newGoalObj.minGoalToday, newGoalObj.maxGoalWeek, newGoalObj.minGoalWeek)

            goalsList.add(newGoalObj)
            txtTodayMaxGoal.text.clear()
            txtTodayMinGoal.text.clear()
            txtWeekMinGoal.text.clear()
            txtWeekMaxGoal.text.clear()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun updateTodayDateStr() {
        val myFormat = "MM/dd/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        TodayDateStr = sdf.format(this.cal.time)
    }

    private fun updateWeekStartStr() {
        val myFormat = "MM/dd/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        WeekStartStr = sdf.format(this.cal.time)
    }

    private fun updateWeekEndStr() {
        val myFormat = "MM/dd/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        WeekEndStr = sdf.format(this.cal.time)
    }

    private fun uploadGoalToFirebase(todayMaxGoal: String, todayMinGoal: String, weekMaxGoal: String, weekMinGoal: String) {
        val goalObj = GoalClass().apply {
            maxGoalToday = todayMaxGoal
            minGoalToday = todayMinGoal
            maxGoalWeek = weekMaxGoal
            minGoalWeek = weekMinGoal
        }

        database.getReference("goals").push().setValue(goalObj)
            .addOnSuccessListener {
                Toast.makeText(this, "Goal added successfully", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed to add goal: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
