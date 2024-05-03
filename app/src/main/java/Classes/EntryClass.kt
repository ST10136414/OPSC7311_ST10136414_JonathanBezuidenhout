package Classes

class EntryClass()
{
    lateinit var projectName:String
    lateinit var note:String
    var loggedTime:Int = 0
    var startTime: Int = 0
    var endTime: Int = 0
    lateinit var user: String


    companion object {
        @JvmStatic
        val entryMutableList = mutableListOf<EntryClass>()
        //lateinit var loggedUser: UserClass
    }

    constructor(pName: String):this()
    {
        projectName= pName
    }
    constructor(pName: String, pNote: String, lTime: Int, tStart: Int, tEnd: Int) : this(pName)
    {
        projectName = pName
        note = pNote
        loggedTime = lTime
        startTime = tStart
        endTime = tEnd
    }

}