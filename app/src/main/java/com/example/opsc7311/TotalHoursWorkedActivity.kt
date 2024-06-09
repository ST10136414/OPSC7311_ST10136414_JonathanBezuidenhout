package com.example.opsc7311

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class TotalHoursWorkedActivity : AppCompatActivity() {

    private lateinit var etStartDate: TextView
    private lateinit var etEndDate : TextView
    private lateinit var lineChart: LineChart
    private lateinit var btnDisplayHours:Button
    private var cal = Calendar.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_total_hours_worked)

        lineChart = findViewById(R.id.lineChart)
        etStartDate = findViewById<TextView>(R.id.selectStartDateForTotalHours)
        etEndDate = findViewById<TextView>(R.id.selectEndDateTotalHours)
        btnDisplayHours = findViewById<Button>(R.id.btnDisplayTotalHours)
        val entries = ArrayList<Entry>()
        entries.add(Entry(1f, 1f))
        entries.add(Entry(2f, 2f))
        entries.add(Entry(3f, 0f))
        entries.add(Entry(4f, 4f))
        entries.add(Entry(5f, 3f))

        val dataSet = LineDataSet(entries, "Label")
        dataSet.color = getColor(R.color.red)
        dataSet.valueTextColor = getColor(R.color.grey)

        val lineData = LineData(dataSet)
        lineChart.data = lineData

        val description = Description()
        description.text = "Sample Line Chart"
        lineChart.description = description

        lineChart.invalidate() // refresh


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

        //btnDisplayHours.setOnClickListener()
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