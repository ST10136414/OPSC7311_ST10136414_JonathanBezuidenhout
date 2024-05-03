package Classes

import java.util.Date

class ProjectClass
{
    lateinit var projectName:String
    lateinit var projectColour:String
    lateinit var clientName:String
    lateinit var startDate: String
    lateinit var endDate: String
    lateinit var budget: String
    lateinit var hourlyRate: String
    var totaltime: String = ""


    companion object {
        @JvmStatic
        val projectMutableList = mutableListOf<ProjectClass>()
        //list of timesheets?
        //lateinit var loggedUser: UserClass
    }


}