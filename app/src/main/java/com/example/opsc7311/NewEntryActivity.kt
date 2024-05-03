package com.example.opsc7311
import Classes.EntryClass
import Classes.ProjectClass
import Classes.StartAndEndTimeClass
import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import androidx.activity.enableEdgeToEdge
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.security.KeyStore.Entry

class NewEntryActivity : AppCompatActivity()
{
    private lateinit var btnCreateNewEntry: ImageButton
    private lateinit var txtLoggedTime:TextView
    private lateinit var spinSelectedProjectName:Spinner
    private lateinit var selectedProjectName:String
    private var firstTime: Calendar? = null
    private var secondTime: Calendar? = null
    private lateinit var startTime : String
    private lateinit var endTime:String
    private lateinit var btnFrom:TextView
    private lateinit var btnTo:TextView
    private lateinit var note:TextInputEditText
    private lateinit var btnPlan:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_new_entry)
        btnCreateNewEntry = findViewById<ImageButton>(R.id.imgButtonAddEntry)
        txtLoggedTime = findViewById<TextView>(R.id.txtDisplayLoggedTime)
        spinSelectedProjectName = findViewById<Spinner>(R.id.spinSelectProject)
        btnFrom = findViewById<TextView>(R.id.txtFrom)
        btnTo = findViewById<TextView>(R.id.txtTo)
        note = findViewById<TextInputEditText>(R.id.txtNote)
        btnPlan = findViewById<TextView>(R.id.txtPlan)
        btnFrom.setOnClickListener {
            showTimePicker { calendar ->
                firstTime = calendar
                //txtLoggedTime.text = "First Time: ${formatTime(calendar)}"
                startTime = "${formatTime(calendar)}"
                Toast.makeText(this, startTime, Toast.LENGTH_SHORT).show()
                calculateTimeDifference()
            }
        }
        btnTo.setOnClickListener {
            showTimePicker { calendar ->
                secondTime = calendar
                //txtLoggedTime.text = "First Time: ${formatTime(calendar)}"
                endTime = "${formatTime(calendar)}"
                calculateTimeDifference()
            }
        }
            spinSelectedProjectName.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    // Get the selected item as a string
                    val selectedItem = parent.getItemAtPosition(position).toString()
                    selectedProjectName = selectedItem
                    // Perform actions with the selected item
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        btnCreateNewEntry.setOnClickListener()
        {
            val entryObj = EntryClass()
            entryObj.loggedTime = txtLoggedTime.text.toString()
           // entryObj.selectedProjectName = selectedProjectName
            entryObj.startTime = startTime
            entryObj.endTime = endTime
            entryObj.note = note.text.toString()
            EntryClass.entryMutableList.add(entryObj)
            val listSize = EntryClass.entryMutableList.size
            Toast.makeText(this, listSize.toString(), Toast.LENGTH_SHORT).show()
            val navMreIntent = Intent(this, NewEntryActivity::class.java)
            startActivity(navMreIntent)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.territoryHeadingTxt)) { v, insets ->
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
            txtLoggedTime.text = "$timeDifference"
        }
    }
}
