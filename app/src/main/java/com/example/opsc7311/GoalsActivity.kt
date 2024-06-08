package com.example.opsc7311

import Classes.GoalClass
import Classes.ProjectClass
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
import org.w3c.dom.Text
import java.util.Calendar
import java.text.SimpleDateFormat


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

    private var TodayTime: Calendar? = null

    private var weekStartTime: Calendar? = null
    private var weekEndTime: Calendar? = null


    private lateinit var btnTodayDate: ImageView
    private lateinit var btnWeekStart: ImageView
    private lateinit var btnWeekEnd: ImageView


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











        btnTodayDate.setOnClickListener {
            showTimePicker { calendar ->
                TodayTime = calendar
                //txtLoggedTime.text = "First Time: ${formatTime(calendar)}"
                TodayDateStr = "${formatTime(calendar)}"
                Toast.makeText(this, TodayDateStr, Toast.LENGTH_SHORT).show()
            }
        }


        btnWeekStart.setOnClickListener {
            showTimePicker { calendar ->
                weekStartTime = calendar
                WeekStartStr = "${formatTime(calendar)}"
                Toast.makeText(this, WeekStartStr, Toast.LENGTH_SHORT).show()
            }
        }
        btnWeekEnd.setOnClickListener {
            showTimePicker { calendar ->
                weekEndTime = calendar
                WeekEndStr = "${formatTime(calendar)}"
                Toast.makeText(this, WeekEndStr, Toast.LENGTH_SHORT).show()
            }
        }








        btnCreateNewGoal.setOnClickListener()
        {
            val newGoalobj = GoalClass()
            newGoalobj.maxGoalToday = txtTodayMaxGoal.text.toString()
            newGoalobj.minGoalToday = txtTodayMinGoal.text.toString()
            newGoalobj.maxGoalWeek = txtWeekMaxGoal.text.toString()
            newGoalobj.minGoalWeek = txtWeekMinGoal.text.toString()

            newGoalobj.TodayDate= TodayDateStr
            newGoalobj.WeekStartDate = WeekStartStr
            newGoalobj.WeekEndDate = WeekEndStr



            goalsList.add(newGoalobj)
            txtTodayMaxGoal.text.clear()
            txtTodayMinGoal.text.clear()
            txtWeekMinGoal.text.clear()
            txtWeekMaxGoal.text.clear()

            Toast.makeText(this, "Goal Saved!", Toast.LENGTH_SHORT).show()

        }













        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }


    private fun showTimePicker(onTimeSelected: (Calendar) -> Unit) {
        // Get the current time
        val calendar = Calendar.getInstance()
        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        val currentMinute = calendar.get(Calendar.MINUTE)

        // Create the TimePickerDialog
        val timePickerDialog = TimePickerDialog(
            this,
            { _, hourOfDay, minute ->
                // Create a Calendar object with the selected time
                val selectedCalendar = Calendar.getInstance().apply {
                    set(Calendar.HOUR_OF_DAY, hourOfDay)
                    set(Calendar.MINUTE, minute)
                }

                // Call the callback function with the selected time
                onTimeSelected(selectedCalendar)
            },
            currentHour,
            currentMinute,
            true // 24-hour format
        )
        timePickerDialog.show()
    }
    private fun formatTime(calendar: Calendar): String {
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        return String.format("%02d:%02d", hour, minute)
    }

}