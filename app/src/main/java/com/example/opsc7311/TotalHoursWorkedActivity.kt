package com.example.opsc7311

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

class TotalHoursWorkedActivity : AppCompatActivity() {

    private lateinit var lineChart: LineChart
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_total_hours_worked)

        lineChart = findViewById(R.id.lineChart)

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
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }




}