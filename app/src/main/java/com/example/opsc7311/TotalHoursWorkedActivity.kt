package com.example.opsc7311

import Classes.EntryClass
import android.app.DatePickerDialog
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.DatePicker
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class TotalHoursWorkedActivity : AppCompatActivity() {

    private lateinit var etStartDate: TextView
    private lateinit var etEndDate : TextView
    private lateinit var lineChart: LineChart
    private lateinit var btnDisplayHours:Button
    private lateinit var database: FirebaseDatabase
    private lateinit var projectsRef: DatabaseReference
    private var entriesList = mutableListOf<EntryClass>()
    private var cal = Calendar.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_total_hours_worked)
        database = FirebaseDatabase.getInstance()
        projectsRef = database.getReference("projects")
        lineChart = findViewById(R.id.lineChart)
        btnDisplayHours = findViewById(R.id.btnDisplayTotalHours)
        etStartDate = findViewById(R.id.selectStartDateForTotalHours)
        etEndDate = findViewById(R.id.selectEndDateTotalHours)


// Set the bitmap as the source of your ImageView

       // val entries = ArrayList<Entry>()
       // entries.add(Entry(1f, 1f))
       // entries.add(Entry(2f, 2f))
       // entries.add(Entry(3f, 0f))
       // entries.add(Entry(4f, 4f))
       /// entries.add(Entry(5f, 3f))

      // // val dataSet = LineDataSet(entries, "Label")
       // dataSet.color = getColor(R.color.red)
       // dataSet.valueTextColor = getColor(R.color.grey)

        //val lineData = LineData(dataSet)
        //lineChart.data = lineData

        //val description = Description()
       // description.text = "Sample Line Chart"
       // lineChart.description = description

       // lineChart.invalidate() // refresh


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

        etStartDate.setOnClickListener()
        {
            DatePickerDialog(this@TotalHoursWorkedActivity,startDateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }

        etEndDate.setOnClickListener()
        {
            DatePickerDialog(this@TotalHoursWorkedActivity,endDateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }

        btnDisplayHours.setOnClickListener()
        {
            fetchEntriesAndDisplayChart()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun fetchEntriesAndDisplayChart() {
        try {
            val startDate = SimpleDateFormat("MM/dd/yyyy", Locale.US).parse(etStartDate.text.toString())
            val endDate = SimpleDateFormat("MM/dd/yyyy", Locale.US).parse(etEndDate.text.toString())

            if (startDate != null && endDate != null) {
                val startTimestamp = startDate.time
                val endTimestamp = endDate.time

                projectsRef.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        entriesList.clear()
                        for (projectSnapshot in dataSnapshot.children) {
                            val projectEntriesRef = projectSnapshot.child("entries")
                            projectEntriesRef.ref.addListenerForSingleValueEvent(object : ValueEventListener {
                                override fun onDataChange(entriesSnapshot: DataSnapshot) {
                                    for (entrySnapshot in entriesSnapshot.children) {
                                        val dateCompletedString = entrySnapshot.child("dateCompleted").getValue(String::class.java)
                                        val dateCompleted = dateCompletedString?.let {
                                            SimpleDateFormat("MM/dd/yyyy", Locale.US).parse(
                                                it
                                            )
                                        }
                                        val entryTimestamp = dateCompleted?.time ?: continue
                                        if (entryTimestamp in startTimestamp..endTimestamp) {
                                            val entry = entrySnapshot.getValue(EntryClass::class.java)
                                            entry?.let {
                                                entriesList.add(it)
                                            }
                                        }
                                    }
                                    populateChartWithEntries()
                                }

                                override fun onCancelled(databaseError: DatabaseError) {
                                    Log.e("FirebaseError", "Failed to load entries: ${databaseError.message}")
                                    Toast.makeText(this@TotalHoursWorkedActivity, "Failed to load entries", Toast.LENGTH_SHORT).show()
                                }
                            })
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        Log.e("FirebaseError", "Failed to load projects: ${databaseError.message}")
                        Toast.makeText(this@TotalHoursWorkedActivity, "Failed to load projects", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        } catch (e: ParseException) {
            Log.e("ParseException", "Error parsing date: ${e.message}")
            Toast.makeText(this@TotalHoursWorkedActivity, "Error parsing date", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Log.e("Exception", "An error occurred: ${e.message}")
            Toast.makeText(this@TotalHoursWorkedActivity, "An error occurred", Toast.LENGTH_SHORT).show()
        }
    }
    private fun populateChartWithEntries() {
        if (entriesList.isEmpty()) {
            Toast.makeText(this, "No entries found in the selected date range", Toast.LENGTH_SHORT).show()
            return
        }

        val entries = ArrayList<Entry>()
        val dateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.US)
        val dateToHoursMap = mutableMapOf<String, Float>()

        for (entry in entriesList) {
            try {
                val dateCompleted = SimpleDateFormat("MM/dd/yyyy", Locale.US).parse(entry.dateCompleted)
                val date = dateFormat.format(dateCompleted)
                val timeString = entry.loggedTime

                // Extract numeric part of the string (assuming the format is "X hours Y minutes")
                val numericTimeString = timeString.split(" ")[0]

                // Parse the numeric part as a float
                val hours = numericTimeString.toFloat()

                if (dateToHoursMap.containsKey(date)) {
                    dateToHoursMap[date] = dateToHoursMap[date]!! + hours
                } else {
                    dateToHoursMap[date] = hours
                }
            } catch (e: ParseException) {
                Log.e("ParseException", "Error parsing date: ${entry.dateCompleted}", e)
            } catch (e: NumberFormatException) {
                Log.e("NumberFormatException", "Error parsing time: ${entry.loggedTime}", e)
            }
        }

        var index = 1f
        for ((date, hours) in dateToHoursMap) {
            entries.add(Entry(index, hours))
            index++
        }

        if (entries.isEmpty()) {
            Toast.makeText(this, "No data available for the chart", Toast.LENGTH_SHORT).show()
            return
        }

        val dataSet = LineDataSet(entries, "Total Hours Worked")
        dataSet.color = getColor(R.color.red)
        dataSet.valueTextColor = getColor(R.color.grey)

        val lineData = LineData(dataSet)
        lineChart.data = lineData

        val description = Description()
        description.text = "Total Hours Worked Over Time"
        lineChart.description = description

        lineChart.invalidate() // refresh

        // Capture the chart as a bitmap and set it as the image source for the ImageView
        val imageView: ImageView = findViewById(R.id.timeEntryDisplay)
        val bitmap = Bitmap.createBitmap(lineChart.width, lineChart.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        lineChart.draw(canvas)
        imageView.setImageBitmap(bitmap)
    }

    private fun calculateHours(startTime: String, endTime: String): Float {
        val dateFormat = SimpleDateFormat("HH:mm", Locale.US)
        val start = dateFormat.parse(startTime)
        val end = dateFormat.parse(endTime)

        return if (start != null && end != null) {
            val diff = end.time - start.time
            diff / (1000 * 60 * 60).toFloat()
        } else {
            0f
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