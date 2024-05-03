package Classes
import android.os.Parcel
import android.os.Parcelable

class ProjectClass(
    var projectName: String = "",
    var projectColour: String = "",
    var clientName: String = "",
    var startDate: String = "",
    var endDate: String = "",
    var budget: String = "",
    var hourlyRate: String = ""
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(projectName)
        parcel.writeString(projectColour)
        parcel.writeString(clientName)
        parcel.writeString(startDate)
        parcel.writeString(endDate)
        parcel.writeString(budget)
        parcel.writeString(hourlyRate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProjectClass> {
        override fun createFromParcel(parcel: Parcel): ProjectClass {
            return ProjectClass(parcel)
        }

        override fun newArray(size: Int): Array<ProjectClass?> {
            return arrayOfNulls(size)
        }
    }
}

/*
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
}*/