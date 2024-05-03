package Classes

import android.graphics.Bitmap

class EntryClass
{
    lateinit var selectedProjectName:String
    lateinit var note:String
    lateinit var loggedTime:String
    lateinit var startTime: String
    lateinit var endTime: String
    lateinit var plannedTime:String
    lateinit var user: String

    companion object {
        @JvmStatic
        val entryMutableList = mutableListOf<EntryClass>()
         val capturedImages = mutableListOf<Bitmap>()
    }






}