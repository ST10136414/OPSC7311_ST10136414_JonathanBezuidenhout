package com.example.opsc7311

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.opsc7311.databinding.ActivityTimeSheetBinding

class TimeSheetActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_time_sheet)

        val binding=ActivityTimeSheetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //
        binding.CalendarButton.setOnClickListener(this)


        //no clue what this does????
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
    }

    override fun onClick(v: View?) {
        //this navigates to the Calendar activity
        openIntent(applicationContext, "parameter text", CalendarActivity::class.java)
    }
}