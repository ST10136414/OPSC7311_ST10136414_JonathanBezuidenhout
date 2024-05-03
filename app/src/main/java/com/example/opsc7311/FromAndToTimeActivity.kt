package com.example.opsc7311

import Classes.ProjectClass
import Classes.StartAndEndTimeClass
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.sql.Time
import java.util.Calendar

class FromAndToTimeActivity : AppCompatActivity() {
   // private var startAndEndTimeClass = StartAndEndTimeClass()
   private val fromAndToTimeList = mutableListOf<StartAndEndTimeClass>()
    private lateinit var btnStartTime: Button
    private lateinit var btnCreateFromToTime: Button
    private lateinit var txtLoggedTime:TextView
    private lateinit var btnEndTime: Button
    private var firstTime: Calendar? = null
    private var secondTime: Calendar? = null
    private lateinit var startTime : String
    private lateinit var endTime:String

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_from_and_to_time)
        btnStartTime = findViewById<Button>(R.id.btnStartTime)
        btnEndTime = findViewById<Button>(R.id.btnEndTime)
        btnCreateFromToTime = findViewById<Button>(R.id.btnCreateFromAndToTIme)
        txtLoggedTime = findViewById<TextView>(R.id.txtCalculatedLoggedTime)
        btnStartTime.setOnClickListener {
            showTimePicker { calendar ->
                firstTime = calendar
                //txtLoggedTime.text = "First Time: ${formatTime(calendar)}"
                startTime = "${formatTime(calendar)}"
                Toast.makeText(this, startTime, Toast.LENGTH_SHORT).show()
                calculateTimeDifference()
            }
        }

        btnEndTime.setOnClickListener {
            showTimePicker { calendar ->
                secondTime = calendar
                //txtLoggedTime.text = "First Time: ${formatTime(calendar)}"
                endTime = "${formatTime(calendar)}"
                calculateTimeDifference()
            }
        }

        btnCreateFromToTime.setOnClickListener()
        {
            val startAndEndTimeClass = StartAndEndTimeClass()
            startAndEndTimeClass.startTime = startTime
            startAndEndTimeClass.endTime = endTime
            startAndEndTimeClass.totalLoggedTime = txtLoggedTime.text.toString()

            fromAndToTimeList.add(startAndEndTimeClass)
            val listSize = fromAndToTimeList.size
            //txtLoggedTime.text.clear()
            //etProjectColour = findViewById<EditText>(R.id.editTxtProj)
           // etClientName.text.clear()
            //etStartDate.text = ""
            //etEndDate.text = ""
            //etBudget.text.clear()
            //etHourlyRate.text.clear()
            Toast.makeText(this, listSize.toString(), Toast.LENGTH_SHORT).show()
            val navMreIntent = Intent(this, NewEntryActivity::class.java)
            startActivity(navMreIntent)
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
    private fun calculateTimeDifference() {
        if (firstTime != null && secondTime != null) {
            val diffMillis = kotlin.math.abs(firstTime!!.timeInMillis - secondTime!!.timeInMillis)
            val diffHours = diffMillis / (1000 * 60 * 60) // convert milliseconds to hours
            val diffMinutes = (diffMillis / (1000 * 60)) % 60 // remaining minutes

            val timeDifference = String.format(
                "%d hours %02d minutes",
                diffHours,
                diffMinutes
            )
            Toast.makeText(this, "Time difference: $timeDifference", Toast.LENGTH_SHORT).show()
            //txtLoggedTime.text = "Time Difference: $timeDifference"
           // Toast.makeText(this, "yes", Toast.LENGTH_SHORT).show()
        }
    }
}
//------------------------------END OF FILE--------------------------------------------------------//