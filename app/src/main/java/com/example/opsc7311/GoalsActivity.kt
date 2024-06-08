package com.example.opsc7311

import Classes.GoalClass
import Classes.ProjectClass
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class GoalsActivity : AppCompatActivity() {
    private lateinit var btnCreateNewGoal:Button
    private lateinit var txtGoalName: TextView
    private lateinit var txtMaxGoal: TextView
    private lateinit var txtMinGoal:TextView
    val goalsList = GoalClass.goalsMutableList
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_goals)
        btnCreateNewGoal = findViewById<Button>(R.id.btnAddGoals)
        txtGoalName = findViewById<TextView>(R.id.txtGoalName)
        txtMinGoal = findViewById<TextView>(R.id.txtMinGoals)
        txtMaxGoal = findViewById<TextView>(R.id.txtMaxGoals)
        btnCreateNewGoal.setOnClickListener()
        {
            val newGoalobj = GoalClass()
            newGoalobj.goalName = txtGoalName.text.toString()
            newGoalobj.maxGoal = txtMaxGoal.text.toString()
            newGoalobj.minGoal = txtMinGoal.text.toString()
            goalsList.add(newGoalobj)
            txtGoalName.text = ""
            //etProjectColour = findViewById<EditText>(R.id.editTxtProj)
            txtMaxGoal.text = ""
            txtMinGoal.text = ""
            txtGoalName.text =""
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}