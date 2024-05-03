package com.example.opsc7311
import Classes.EntryClass
import Classes.ProjectClass
import Classes.StartAndEndTimeClass
import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
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
import java.security.KeyStore.Entry

class NewEntryActivity : AppCompatActivity()
{

    //private val newEntryList = mutableListOf<EntryClass>()
    private lateinit var btnCreateNewEntry: Button
    private lateinit var txtLoggedTime:TextView
    private lateinit var spinSelectedProjectName:Spinner
    private lateinit var selectedProjectName:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_new_entry)
        btnCreateNewEntry = findViewById<Button>(R.id.imgButtonAddEntry)
        txtLoggedTime = findViewById<TextView>(R.id.txtDisplayLoggedTime)
        spinSelectedProjectName = findViewById<Spinner>(R.id.spinSelectProject)
        val startAndEndTimesList = intent.getStringArrayListExtra("startAndEndTimes")
       // if (startAndEndTimesList != null) {
            //getStartAndEndTime(startAndEndTimesList)

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
            entryObj.selectedProjectName = selectedProjectName
            entryObj.startTime = "add ths"// these components do not exist yet
            entryObj.endTime = "add this" // this is not implemented yet

            EntryClass.entryMutableList.add(entryObj)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.territoryHeadingTxt)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



    }
    /*
        fun getStartAndEndTime(startAndEndTimesList: List<String>) {
            for (foundStartAndEndTime in startAndEndTimesList) {
                val projectNameTextView = TextView(this)
              //  txtLoggedTime.text = startAndEndTimesList
                //projectNameTextView.setTextColor(
                    //ContextCompat.getColor(
                        this,
                        android.R.color.white
                    )
                )
                projectsContainer.addView(projectNameTextView)
            }
        }*/
}
