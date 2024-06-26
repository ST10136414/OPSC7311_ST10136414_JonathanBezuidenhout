/*package com.example.opsc7311
import Classes.EntryClass
import Classes.ProjectClass
import Classes.StartAndEndTimeClass
import Classes.UserClass
import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
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
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.io.File
import java.security.KeyStore.Entry
import java.util.Date

import androidx.activity.enableEdgeToEdge
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

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
    private lateinit var btnTakePicture:TextView
    private val REQUEST_IMAGE_CAPTURE = 1
    private lateinit var photoFile: File

    private lateinit var database: FirebaseDatabase
    private lateinit var usersRef: DatabaseReference
    private lateinit var entriesRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_new_entry)

        // Initialize Firebase
        database = FirebaseDatabase.getInstance()
        usersRef = database.getReference("users")

        btnCreateNewEntry = findViewById<ImageButton>(R.id.imgButtonAddEntry)
        txtLoggedTime = findViewById<TextView>(R.id.txtDisplayLoggedTime)

        val spinnerItems = ProjectClass.projectMutableList.map { it.projectName }

        spinSelectedProjectName = findViewById<Spinner>(R.id.spinSelectProject)
        btnFrom = findViewById<TextView>(R.id.txtFrom)
        btnTo = findViewById<TextView>(R.id.txtTo)
        note = findViewById<TextInputEditText>(R.id.txtNote)
        btnPlan = findViewById<TextView>(R.id.txtPlan)
        btnTakePicture = findViewById<TextView>(R.id.txtAddImage)
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

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerItems)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinSelectedProjectName.adapter = adapter


        spinSelectedProjectName.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {

                    // Get the selected item as a string
                    val selectedItem =
                        spinnerItems[position]//parent.getItemAtPosition(position).toString()

                    //val selectedProject = ProjectClass.projectMutableList.find{}
                    selectedProjectName = selectedItem
                    // Perform actions with the selected item
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        btnTakePicture.setOnClickListener()
        {
            checkCameraPermission()
        }

        btnCreateNewEntry.setOnClickListener()
        {
            val entryObj = EntryClass()
            entryObj.loggedTime = txtLoggedTime.text.toString()
            entryObj.selectedProjectName = selectedProjectName
            entryObj.startTime = startTime
            entryObj.endTime = endTime
            entryObj.note = note.text.toString()
            entryObj.user = UserClass.loggedUser.userName.toString()

            //Find project where projectName =  selectedProjectName
            //val project = ProjectClass()
            /*
            val project = ProjectClass.projectMutableList.find { it.projectName==selectedProjectName }
            if (project != null) {
                project.totaltime = (project.totaltime.toInt()+ convertTime(entryObj.loggedTime)).toString()
            }*/


            //stores all previous as entryObj in EntryClass static list
            EntryClass.entryMutableList.add(entryObj)
            val listSize = EntryClass.entryMutableList.size
            Toast.makeText(this, listSize.toString(), Toast.LENGTH_SHORT).show()
            val navMreIntent = Intent(this, NewEntryActivity::class.java)
            startActivity(navMreIntent)
            uploadEntryToFirebase()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.territoryHeadingTxt)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }




        val backBtn = findViewById<ImageView>(R.id.btnBack)
        backBtn.setOnClickListener{
            val DashboardIntent = Intent(this, DashboardActivity::class.java)
            startActivity(DashboardIntent)
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

    private fun checkCameraPermission() {
        if (checkSelfPermission(android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(android.Manifest.permission.CAMERA), CAMERA_PERMISSION_CODE)
        } else {
            openCamera()
        }
    }
    private val cameraActivityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val imageBitmap = result.data?.extras?.get("data") as Bitmap
                imageBitmap?.let {
                    EntryClass.capturedImages.add(it)
                }

                    Toast.makeText(this, "Image captured and saved!", Toast.LENGTH_SHORT).show()

                Toast.makeText(this, "Image captured and saved!", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(this, "Failed to capture image", Toast.LENGTH_SHORT).show()
            }
        }
    private fun openCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraActivityResultLauncher.launch(cameraIntent)
    }
    private fun uploadEntryToFirebase() {
        val loggedUserId = UserClass.loggedUser.userEmail.replace(".", "_") // Ensure UserClass has a userId property for the logged-in user

        val entryObj = EntryClass().apply {
            loggedTime = txtLoggedTime.text.toString()
            selectedProjectName = this@NewEntryActivity.selectedProjectName
            startTime = this@NewEntryActivity.startTime
            endTime = this@NewEntryActivity.endTime
            note = this@NewEntryActivity.note.text.toString()
            user = UserClass.loggedUser.userName.toString()
        }

        val userEntriesRef = usersRef.child(loggedUserId).child("entries")
        val newEntryRef = userEntriesRef.push()
        newEntryRef.setValue(entryObj).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(this, "Entry added successfully", Toast.LENGTH_SHORT).show()
                val navIntent = Intent(this, NewEntryActivity::class.java)
                startActivity(navIntent)
            } else {
                Toast.makeText(this, "Failed to add entry", Toast.LENGTH_SHORT).show()
            }
        }
    }
    companion object {
        private const val CAMERA_PERMISSION_CODE = 101
    }

}
*//*
package com.example.opsc7311

import Classes.EntryClass
import Classes.ProjectClass
import Classes.UserClass
import android.app.Activity
import android.app.TimePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.android.material.textfield.TextInputEditText
import java.io.File
import java.util.*

class NewEntryActivity : AppCompatActivity() {
    private lateinit var btnCreateNewEntry: ImageButton
    private lateinit var txtLoggedTime: TextView
    private lateinit var spinSelectedProjectName: Spinner
    private lateinit var selectedProjectName: String
    private var firstTime: Calendar? = null
    private var secondTime: Calendar? = null
    private lateinit var startTime: String
    private lateinit var endTime: String
    private lateinit var btnFrom: TextView
    private lateinit var btnTo: TextView
    private lateinit var note: TextInputEditText
    private lateinit var btnPlan: TextView
    private lateinit var btnTakePicture: TextView
    private val REQUEST_IMAGE_CAPTURE = 1
    private lateinit var photoFile: File

    private lateinit var database: FirebaseDatabase
    private lateinit var usersRef: DatabaseReference
    private lateinit var entriesRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_new_entry)

        // Initialize Firebase
        database = FirebaseDatabase.getInstance()
        usersRef = database.getReference("users")

        btnCreateNewEntry = findViewById(R.id.imgButtonAddEntry)
        txtLoggedTime = findViewById(R.id.txtDisplayLoggedTime)

        val spinnerItems = ProjectClass.projectMutableList.map { it.projectName }

        spinSelectedProjectName = findViewById(R.id.spinSelectProject)
        btnFrom = findViewById(R.id.txtFrom)
        btnTo = findViewById(R.id.txtTo)
        note = findViewById(R.id.txtNote)
        btnPlan = findViewById(R.id.txtPlan)
        btnTakePicture = findViewById(R.id.txtAddImage)

        btnFrom.setOnClickListener {
            showTimePicker { calendar ->
                firstTime = calendar
                startTime = formatTime(calendar)
                Toast.makeText(this, startTime, Toast.LENGTH_SHORT).show()
                calculateTimeDifference()
            }
        }

        btnTo.setOnClickListener {
            showTimePicker { calendar ->
                secondTime = calendar
                endTime = formatTime(calendar)
                calculateTimeDifference()
            }
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerItems)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinSelectedProjectName.adapter = adapter

        spinSelectedProjectName.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                selectedProjectName = spinnerItems[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }

        btnTakePicture.setOnClickListener {
            checkCameraPermission()
        }

        btnCreateNewEntry.setOnClickListener {
            val entryObj = EntryClass().apply {
                loggedTime = txtLoggedTime.text.toString()
                selectedProjectName = this@NewEntryActivity.selectedProjectName
                startTime = this@NewEntryActivity.startTime
                endTime = this@NewEntryActivity.endTime
                note = this@NewEntryActivity.note.text.toString()
                user = UserClass.loggedUser.userName
            }

            // Store all previous entries in EntryClass static list
            EntryClass.entryMutableList.add(entryObj)
            val listSize = EntryClass.entryMutableList.size
            Toast.makeText(this, listSize.toString(), Toast.LENGTH_SHORT).show()
            val navMreIntent = Intent(this, NewEntryActivity::class.java)
            startActivity(navMreIntent)
            uploadEntryToFirebase(entryObj)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.territoryHeadingTxt)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun showTimePicker(onTimeSelected: (Calendar) -> Unit) {
        val calendar = Calendar.getInstance()
        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        val currentMinute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            this,
            { _, hourOfDay, minute ->
                val selectedCalendar = Calendar.getInstance().apply {
                    set(Calendar.HOUR_OF_DAY, hourOfDay)
                    set(Calendar.MINUTE, minute)
                }
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
            val diffHours = diffMillis / (1000 * 60 * 60)
            val diffMinutes = (diffMillis / (1000 * 60)) % 60

            val timeDifference = String.format("%d hours %02d minutes", diffHours, diffMinutes)
            Toast.makeText(this, "Time difference: $timeDifference", Toast.LENGTH_SHORT).show()
            txtLoggedTime.text = timeDifference
        }
    }

    private fun checkCameraPermission() {
        if (checkSelfPermission(android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(android.Manifest.permission.CAMERA), CAMERA_PERMISSION_CODE)
        } else {
            openCamera()
        }
    }

    private val cameraActivityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val imageBitmap = result.data?.extras?.get("data") as Bitmap
            imageBitmap?.let {
                EntryClass.capturedImages.add(it)
            }
            Toast.makeText(this, "Image captured and saved!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Failed to capture image", Toast.LENGTH_SHORT).show()
        }
    }

    private fun openCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraActivityResultLauncher.launch(cameraIntent)
    }

    private fun uploadEntryToFirebase(entryObj: EntryClass) {
        val loggedUserId = UserClass.loggedUser.userEmail.replace(".", "_")

        val userEntriesRef = usersRef.child(loggedUserId).child("entries")
        val newEntryRef = userEntriesRef.push()
        newEntryRef.setValue(entryObj).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(this, "Entry added successfully", Toast.LENGTH_SHORT).show()
                val navIntent = Intent(this, NewEntryActivity::class.java)
                startActivity(navIntent)
            } else {
                Toast.makeText(this, "Failed to add entry", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        private const val CAMERA_PERMISSION_CODE = 101
    }
}
*/
package com.example.opsc7311

import Classes.EntryClass
import Classes.ProjectClass
import Classes.UserClass
import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.Calendar
import java.util.Locale

class NewEntryActivity : AppCompatActivity() {
    private lateinit var btnCreateNewEntry: ImageButton
    private lateinit var txtLoggedTime: TextView
    private lateinit var spinSelectedProjectName: Spinner
    private lateinit var selectedProjectName: String
    private lateinit var selectedProjectID: String
    private var firstTime: Calendar? = null
    private var secondTime: Calendar? = null
    private lateinit var startTime: String
    private lateinit var endTime: String
    private lateinit var btnFrom: TextView
    private lateinit var btnTo: TextView
    private lateinit var note: TextInputEditText
    private lateinit var btnTakePicture: TextView
    private lateinit var btnDateCompleted:TextView
    private lateinit var database: FirebaseDatabase
    private lateinit var projectsRef: DatabaseReference
    private lateinit var txtDateCompleted:String
    private var cal = Calendar.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_new_entry)

        // Initialize Firebase
        database = FirebaseDatabase.getInstance()
        projectsRef = database.getReference("projects")

        btnCreateNewEntry = findViewById(R.id.imgButtonAddEntry)
        txtLoggedTime = findViewById(R.id.txtDisplayLoggedTime)
        spinSelectedProjectName = findViewById(R.id.spinSelectProject)
        btnFrom = findViewById(R.id.txtFrom)
        btnTo = findViewById(R.id.txtTo)
        note = findViewById(R.id.txtNote)
        btnTakePicture = findViewById(R.id.txtAddImage)
        btnDateCompleted = findViewById(R.id.txtDateCompleted)
        // Load projects from Firebase and populate the spinner
        loadProjectsFromFirebase()

        val completedDateSetListener = object: DatePickerDialog.OnDateSetListener{
            override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                cal.set(Calendar.YEAR,year)
                cal.set(Calendar.MONTH,month)
                cal.set(Calendar.DAY_OF_MONTH,dayOfMonth)
                updateCompletedTimeEditText()
            }
        }

        btnFrom.setOnClickListener {
            showTimePicker { calendar ->
                firstTime = calendar
                startTime = formatTime(calendar)
                Toast.makeText(this, startTime, Toast.LENGTH_SHORT).show()
                calculateTimeDifference()
            }
        }

        btnTo.setOnClickListener {
            showTimePicker { calendar ->
                secondTime = calendar
                endTime = formatTime(calendar)
                calculateTimeDifference()
            }
        }

        btnTakePicture.setOnClickListener {
            checkCameraPermission()
        }

        btnDateCompleted.setOnClickListener{
            DatePickerDialog(this@NewEntryActivity,completedDateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }

        btnCreateNewEntry.setOnClickListener {
            val entryObj = EntryClass().apply {
                loggedTime = txtLoggedTime.text.toString()
                selectedProjectName = this@NewEntryActivity.selectedProjectName
                startTime = this@NewEntryActivity.startTime
                endTime = this@NewEntryActivity.endTime
                note = this@NewEntryActivity.note.text.toString()
                user = UserClass.loggedUser.userName
                dateCompleted = txtDateCompleted
            }

            // Store all previous entries in EntryClass static list
            EntryClass.entryMutableList.add(entryObj)
            val listSize = EntryClass.entryMutableList.size
            Toast.makeText(this, listSize.toString(), Toast.LENGTH_SHORT).show()
            uploadEntryToFirebase(entryObj, selectedProjectID)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.territoryHeadingTxt)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun loadProjectsFromFirebase() {
        projectsRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val projectNames = mutableListOf<String>()
                for (projectSnapshot in dataSnapshot.children) {
                    val project = projectSnapshot.getValue(ProjectClass::class.java)
                    project?.let {
                        ProjectClass.projectMutableList.add(it)
                        projectNames.add(it.projectName)
                    }
                }
                setupSpinner(dataSnapshot, projectNames)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@NewEntryActivity, "Failed to load projects", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setupSpinner(dataSnapshot: DataSnapshot, projectNames: List<String>) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, projectNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinSelectedProjectName.adapter = adapter

        spinSelectedProjectName.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                selectedProjectName = projectNames[position]
                selectedProjectID = getProjectIDByName(dataSnapshot, selectedProjectName)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }
    }

    private fun getProjectIDByName(dataSnapshot: DataSnapshot, projectName: String): String {
        for (projectSnapshot in dataSnapshot.children) {
            val project = projectSnapshot.getValue(ProjectClass::class.java)
            if (project?.projectName == projectName) {
                return projectSnapshot.key ?: ""
            }
        }
        return ""
    }

    //-----------------------------------------------------//
    //Function to update the start date TextView
    private fun updateCompletedTimeEditText()
   {
        val myFormat = "MM/dd/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        txtDateCompleted = sdf.format(this.cal.time)
   }
   //
    private fun showTimePicker(onTimeSelected: (Calendar) -> Unit) {
        val calendar = Calendar.getInstance()
        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        val currentMinute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            this,
            { _, hourOfDay, minute ->
                val selectedCalendar = Calendar.getInstance().apply {
                    set(Calendar.HOUR_OF_DAY, hourOfDay)
                    set(Calendar.MINUTE, minute)
                }
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
            val diffHours = diffMillis / (1000 * 60 * 60)
            val diffMinutes = (diffMillis / (1000 * 60)) % 60

            val timeDifference = String.format("%d hours %02d minutes", diffHours, diffMinutes)
            Toast.makeText(this, "Time difference: $timeDifference", Toast.LENGTH_SHORT).show()
            txtLoggedTime.text = timeDifference
        }
    }

    private fun checkCameraPermission() {
        if (checkSelfPermission(android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(android.Manifest.permission.CAMERA), CAMERA_PERMISSION_CODE)
        } else {
            openCamera()
        }
    }

    private val cameraActivityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val imageBitmap = result.data?.extras?.get("data") as Bitmap
            imageBitmap?.let {
                EntryClass.capturedImages.add(it)
            }
            Toast.makeText(this, "Image captured and saved!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Failed to capture image", Toast.LENGTH_SHORT).show()
        }
    }

    private fun openCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraActivityResultLauncher.launch(cameraIntent)
    }

    private fun uploadEntryToFirebase(entryObj: EntryClass, projectID: String) {
        val projectEntriesRef = projectsRef.child(projectID).child("entries")
        val newEntryRef = projectEntriesRef.push()
        newEntryRef.setValue(entryObj).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(this, "Entry added successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Failed to add entry", Toast.LENGTH_SHORT).show()
                task.exception?.printStackTrace()
            }
        }
    }

    companion object {
        private const val CAMERA_PERMISSION_CODE = 101
    }
}
